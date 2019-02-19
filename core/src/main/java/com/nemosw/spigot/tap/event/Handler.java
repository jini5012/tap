package com.nemosw.spigot.tap.event;

import java.util.*;

final class Handler
{

    private final EnumMap<EntityEventPriority, List<RegisteredEntityListener>> handlersSlots = new EnumMap<>(EntityEventPriority.class);

    private RegisteredEntityListener[] handlers;

    synchronized void register(RegisteredEntityListener handler)
    {
        handlersSlots.computeIfAbsent(handler.executor.priority, entityEventPriority -> new ArrayList<>()).add(handler);
        handlers = null;
    }

    synchronized void unregister(EntityListener listener)
    {
        for (List<RegisteredEntityListener> slot : handlersSlots.values())
        {
            Iterator<RegisteredEntityListener> iterator = slot.iterator();

            while (iterator.hasNext())
            {
                RegisteredEntityListener registration = iterator.next();

                if (registration.listener.equals(listener))
                {
                    iterator.remove();
                    registration.invalidate();
                }
            }
        }

        handlers = null;
    }

    private synchronized void bake()
    {
        if (handlers != null)
            return; // don't re-bake when still valid

        List<RegisteredEntityListener> entries = new ArrayList<>();
        for (Map.Entry<EntityEventPriority, List<RegisteredEntityListener>> entry : handlersSlots.entrySet())
        {
            entries.addAll(entry.getValue());
        }

        handlers = entries.toArray(new RegisteredEntityListener[0]);
    }

    RegisteredEntityListener[] getRegisteredListeners()
    {
        RegisteredEntityListener[] handlers;
        while ((handlers = this.handlers) == null) bake();

        return handlers;
    }

}
