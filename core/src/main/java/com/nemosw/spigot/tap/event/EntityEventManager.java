package com.nemosw.spigot.tap.event;

import com.nemosw.spigot.tap.Tap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;

import java.util.*;

public final class EntityEventManager
{

    private static final EventExecutor EVENT_EXECUTOR = (listener, event) -> ((EventListener) listener).onEvent(event);


    private final Plugin plugin;

    private final InvalidEntityListener invalidEntityListener;

    private final Map<Class<? extends Event>, EventListener> registeredListeners = new HashMap<>();

    private final Map<Class<? extends EntityListener>, List<EntityEventExecutor>> registeredExecutors = new HashMap<>();

    private final Map<Object, EventEntity> entities = new WeakHashMap<>();

    public EntityEventManager(Plugin plugin)
    {
        this.plugin = plugin;
        invalidEntityListener = new InvalidEntityListener(this);

        ASMEventExecutor.registerEvents(invalidEntityListener, plugin);
    }

    private List<EntityEventExecutor> getOrCreateExecutors(Class<? extends EntityListener> clazz)
    {
        List<EntityEventExecutor> executors = registeredExecutors.get(clazz);

        if (executors == null)
        {
            registeredExecutors.put(clazz, executors = ASMEntityEventExecutor.getOrCreateHandlers(clazz));

            for (EntityEventExecutor executor : executors)
            {
                Class<? extends Event> handlerClass = executor.handlerClass;
                EventListener listener = registeredListeners.get(handlerClass);

                if (listener == null)
                {
                    listener = new EventListener();
                    plugin.getServer().getPluginManager().registerEvent(handlerClass, listener, EventPriority.HIGH, EVENT_EXECUTOR, plugin, false);
                    plugin.getLogger().info("Entity listener registered: " + handlerClass.getName());
                    registeredListeners.put(handlerClass, listener);
                }

                listener.addExtractor(executor.entityExtractor);
            }
        }

        return executors;
    }

    public void registerListener(Class<? extends EntityListener> clazz)
    {
        getOrCreateExecutors(clazz);
    }

    public void registerEvents(Entity entity, EntityListener listener)
    {
        if (entity == null)
            throw new NullPointerException("Entity cannot be null");
        if (listener == null)
            throw new NullPointerException("Listener cannot be null");

        List<EntityEventExecutor> executors = getOrCreateExecutors(listener.getClass());

        Object nmsEntity = Tap.ENTITY.getHandle(entity);
        EventEntity eventEntity = this.entities.get(nmsEntity);

        if (eventEntity == null)
            this.entities.put(nmsEntity, eventEntity = new EventEntity());

        eventEntity.register(listener, executors);
    }

    void handleEvent(Entity entity, EventKey key, Event event)
    {
        EventEntity eventEntity = entities.get(Tap.ENTITY.getHandle(entity));

        if (eventEntity != null)
            eventEntity.handleEvent(key, event);
    }

    void removeEntity(LivingEntity entity)
    {
        EventEntity eventEntity = this.entities.remove(entity);

        if (eventEntity != null)
            eventEntity.clear();
    }

    public void unregister()
    {
        HandlerList.unregisterAll(invalidEntityListener);

        for (EventListener listener : registeredListeners.values())
            HandlerList.unregisterAll(listener);

        registeredListeners.clear();
        registeredExecutors.clear();
        entities.clear();
    }

    static final EventKey EVENT_KEY = new EventKey();

    private class EventListener implements Listener
    {
        private final Set<EntityExtractor<? extends Event>> entityExtractors = new LinkedHashSet<>();
        private EntityExtractor<? extends Event>[] baked;

        public void addExtractor(EntityExtractor<? extends Event> extractor)
        {
            if (this.entityExtractors.add(extractor))
                this.baked = null;
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void onEvent(Event event)
        {
            if (this.baked == null)
                this.baked = this.entityExtractors.toArray(new EntityExtractor[0]);

            Class<? extends Event> eventClass = event.getClass();

            for (EntityExtractor entityExtractor : this.baked)
            {
                if (entityExtractor.eventClass.isAssignableFrom(eventClass))
                {
                    Entity entity = entityExtractor.getEntity(event);

                    if (entity != null)
                        handleEvent(entity, EVENT_KEY.set(eventClass, entityExtractor), event);
                }
            }
        }
    }

}
