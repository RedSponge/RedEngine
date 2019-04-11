package com.redsponge.redengine.map.events;

public abstract class EventChangeListener {

    /**
     * Called when a new event is selected or created
     *
     * @param newEvent The new event selected
     * @param created Was the event just created?
     */
    public void selectedNewEvent(EventTile newEvent, boolean created) {}

    public void deselectedEvent(EventTile lastEvent) { }

    public void deletedEvent(EventTile deleted) { }
}
