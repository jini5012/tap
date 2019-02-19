package com.nemosw.spigot.tap.event;

import com.nemosw.mox.collections.LinkedNodeList;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class EventEntity
{

    private final Map<EventKey, Handler> handlers = new HashMap<>();

    void register(EntityListener listener, List<EntityEventExecutor> executors)
    {
        for (EntityEventExecutor executor : executors)
        {
            RegisteredEntityListener registration = new RegisteredEntityListener(listener, executor);
            EventKey key = executor.eventKey;
            Handler handler = handlers.get(key);

            if (handler == null)
                handlers.put(key, handler = new Handler());

            handler.register(registration);
        }
    }

    void unregister(EntityListener listener)
    {
        for (Handler handler : handlers.values())
        {
            handler.unregister(listener);
        }
    }

    void handleEvent(EventKey key, Event event)
    {
        Handler handler = this.handlers.get(key);

        if (handler != null)
        {
            for (RegisteredEntityListener registration : handler.getRegisteredListeners())
            {
                if (registration.isValid())
                    registration.execute(event);
            }
        }
    }
}
