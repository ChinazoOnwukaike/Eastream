package com.eastream.eastream.screens.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lcom/eastream/eastream/screens/login/LoadingState;", "", "status", "Lcom/eastream/eastream/screens/login/LoadingState$Status;", "message", "", "(Lcom/eastream/eastream/screens/login/LoadingState$Status;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getStatus", "()Lcom/eastream/eastream/screens/login/LoadingState$Status;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "Status", "app_debug"})
public final class LoadingState {
    @org.jetbrains.annotations.NotNull()
    private final com.eastream.eastream.screens.login.LoadingState.Status status = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String message = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.eastream.eastream.screens.login.LoadingState.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.eastream.eastream.screens.login.LoadingState IDLE = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.eastream.eastream.screens.login.LoadingState SUCCESS = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.eastream.eastream.screens.login.LoadingState FAILED = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.eastream.eastream.screens.login.LoadingState LOADINGS = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.eastream.eastream.screens.login.LoadingState copy(@org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.login.LoadingState.Status status, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public LoadingState(@org.jetbrains.annotations.NotNull()
    com.eastream.eastream.screens.login.LoadingState.Status status, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.eastream.eastream.screens.login.LoadingState.Status component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.eastream.eastream.screens.login.LoadingState.Status getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/eastream/eastream/screens/login/LoadingState$Status;", "", "(Ljava/lang/String;I)V", "SUCCESS", "FAILED", "LOADING", "IDLE", "app_debug"})
    public static enum Status {
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ FAILED /* = new FAILED() */,
        /*public static final*/ LOADING /* = new LOADING() */,
        /*public static final*/ IDLE /* = new IDLE() */;
        
        Status() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/eastream/eastream/screens/login/LoadingState$Companion;", "", "()V", "FAILED", "Lcom/eastream/eastream/screens/login/LoadingState;", "getFAILED", "()Lcom/eastream/eastream/screens/login/LoadingState;", "IDLE", "getIDLE", "LOADINGS", "getLOADINGS", "SUCCESS", "getSUCCESS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.eastream.eastream.screens.login.LoadingState getIDLE() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.eastream.eastream.screens.login.LoadingState getSUCCESS() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.eastream.eastream.screens.login.LoadingState getFAILED() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.eastream.eastream.screens.login.LoadingState getLOADINGS() {
            return null;
        }
    }
}