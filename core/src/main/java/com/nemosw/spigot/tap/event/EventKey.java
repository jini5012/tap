package com.nemosw.spigot.tap.event;

final class EventKey
{

    private Class<?> eventClass;

    private EntityExtractor<?> extractor;

    private int hashCode;

    EventKey set(Class<?> eventClass, EntityExtractor<?> extractor)
    {
        this.eventClass = eventClass;
        this.extractor = extractor;
        this.hashCode = eventClass.hashCode() ^ extractor.hashCode();

        return this;
    }

    @Override
    public int hashCode()
    {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof EventKey)
        {
            EventKey other = (EventKey) obj;

            return this.eventClass == other.eventClass && this.extractor == other.extractor;
        }

        return false;
    }

}
