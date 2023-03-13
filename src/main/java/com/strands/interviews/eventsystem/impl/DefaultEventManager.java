package com.strands.interviews.eventsystem.impl;

import com.strands.interviews.eventsystem.EventManager;
import com.strands.interviews.eventsystem.InterviewEvent;
import com.strands.interviews.eventsystem.InterviewEventListener;


import java.util.*;

/**
 * Manages the firing and receiving of events.
 *
 * <p>Any event passed to {@link #publishEvent} will be passed through to "interested" listeners+
 *=
 * <p>Event listeners can register to receive events via
 * {@link #registerListener(String, com.strands.interviews.eventsystem.InterviewEventListener)}
 */
public class DefaultEventManager implements EventManager
{
    private Map listeners = new HashMap();
    private Map listenersByClass = new HashMap();

    public void publishEvent(InterviewEvent event)
    {
        if (event == null)
        {
            System.err.println("Null event fired?");
            return;
        }
        System.out.println("event published");

        sendEventTo(event, calculateListeners(event.getClass()));
    }
<<<<<<< HEAD
    
    /**
     *  Added new condition to also retrieve the special listeners with null class
     */
    private Collection calculateListeners(Class eventClass)  
    {  
        Collection listeners = (Collection) listenersByClass.get(eventClass);
        if (listeners == null) {
            listeners = (Collection) listenersByClass.get(null);
        }
        return listeners;
=======

    private Collection calculateListeners(Class eventClass)  //Modified
    {  // System.out.println( (Collection) listenersByClass.get(eventClass));
       // return (Collection) listenersByClass.get(eventClass); //array list method
         Collection listeners = (Collection) listenersByClass.get(eventClass);
    if (listeners == null) {
        listeners = (Collection) listenersByClass.get(null);
    }
    return listeners;
>>>>>>> 66db683bc2aa875ffe67985e92b7f5b012854619
    }

    public void registerListener(String listenerKey, InterviewEventListener listener)
    {
        if (listenerKey == null || listenerKey.equals(""))
            throw new IllegalArgumentException("Key for the listener must not be null: " + listenerKey);

        if (listener == null)
            throw new IllegalArgumentException("The listener must not be null: " + listener);

        if (listeners.containsKey(listenerKey))
            unregisterListener(listenerKey);

        Class[] classes = listener.getHandledEventClasses();
<<<<<<< HEAD
        
        /**
         *  Adding the listener to listeners List. When we call handle event
         *   in sendEventTo() it will handle the event if this is in the list
        */
        if(classes.length==0)
            addToListenerList(null, listener);
          
        else     
            for (int i = 0; i < classes.length; i++)
                addToListenerList(classes[i], listener);
          
=======
        if(classes.length==0){
                 //adding the listener to listeners List. When we call handle event in +
                // sendEventTo() it will handle the event as this is in the list
                 addToListenerList(null, listener);
          
        } else     
            for (int i = 0; i < classes.length; i++){
               System.out.println("loop clases"+ classes.length);
               addToListenerList(classes[i], listener);
        
     }
>>>>>>> 66db683bc2aa875ffe67985e92b7f5b012854619
        listeners.put(listenerKey, listener);
    }

    public void unregisterListener(String listenerKey)
    {
        InterviewEventListener listener = (InterviewEventListener) listeners.get(listenerKey);

        for (Iterator it = listenersByClass.values().iterator(); it.hasNext();)
        {
            List list = (List) it.next();
            list.remove(listener);
        }

        listeners.remove(listenerKey);
    }

    private void sendEventTo(InterviewEvent event, Collection listeners)
    {
        if (listeners == null || listeners.size() == 0)
            return;

        for (Iterator it = listeners.iterator(); it.hasNext();)
        {
            InterviewEventListener eventListener = (InterviewEventListener) it.next();
            eventListener.handleEvent(event);
        }
    }

    private void addToListenerList(Class aClass, InterviewEventListener listener)
    {
        if (!listenersByClass.containsKey(aClass))
            listenersByClass.put(aClass, new ArrayList());

        ((List)listenersByClass.get(aClass)).add(listener);
    }

    public Map getListeners()
    {
        return listeners;
    }
}
