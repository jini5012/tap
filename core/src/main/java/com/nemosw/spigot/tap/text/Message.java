package com.nemosw.spigot.tap.text;

class Message
{
    String text;

    Message(String text)
    {
        this.text = text;
    }

    Message() {}

    void toString(StringBuilder builder)
    {
        builder.append('\"').append(this.text).append('\"');
    }

    @Override
    public String toString()
    {
        return text;
    }
}
