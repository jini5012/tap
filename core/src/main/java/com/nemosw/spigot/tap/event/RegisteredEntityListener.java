package com.nemosw.spigot.tap.event;

import com.nemosw.mox.collections.Node;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

final class RegisteredEntityListener
{

    final EntityListener listener;

    final EntityEventExecutor executor;

    private boolean valid;

    RegisteredEntityListener(EntityListener listener, EntityEventExecutor executor)
    {
        this.listener = listener;
        this.executor = executor;
        this.valid = true;
    }

    void execute(Event event)
    {
        if (executor.ignoreCancelled && event instanceof Cancellable && ((Cancellable) event).isCancelled())
            return;

        try
        {
            this.executor.execute(this.listener, event);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    void invalidate()
    {
        this.valid = false;
    }

    boolean isValid()
    {
        return valid;
    }
}
