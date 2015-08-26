package com.soffid.iam.sync.engine;

public class TaskHandlerLog {
    DispatcherHandler dispatcher;
    /** primera vez que se ejecutó */
    long first;
    /** última vez que se ejecutó */
    long last;
    /** cuándo se volverá a ejecutar */
    long next;
    /** número de veces que se ha ejecutado */
    int number;
    /** true si la ejecución ha sido correcta */
    boolean complete;
    /** causas del error (si no ha sido correcta */
    String reason;

    String stackTrace;
    
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DispatcherHandler getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(DispatcherHandler dispatcher) {
        this.dispatcher = dispatcher;
    }

    public long getFirst() {
        return first;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public long getNext() {
        return next;
    }

    public void setNext(long next) {
        this.next = next;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
