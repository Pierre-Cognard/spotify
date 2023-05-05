//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `MusicManager.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package org.example.MusicManager;

public interface MusicServerPrx extends com.zeroc.Ice.ObjectPrx
{
    default Music searchMusic(String title, String artist)
    {
        return searchMusic(title, artist, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default Music searchMusic(String title, String artist, java.util.Map<String, String> context)
    {
        return _iceI_searchMusicAsync(title, artist, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Music> searchMusicAsync(String title, String artist)
    {
        return _iceI_searchMusicAsync(title, artist, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Music> searchMusicAsync(String title, String artist, java.util.Map<String, String> context)
    {
        return _iceI_searchMusicAsync(title, artist, context, false);
    }

    /**
     * @hidden
     * @param iceP_title -
     * @param iceP_artist -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Music> _iceI_searchMusicAsync(String iceP_title, String iceP_artist, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Music> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "searchMusic", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_title);
                     ostr.writeString(iceP_artist);
                 }, istr -> {
                     Music ret;
                     ret = Music.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    default boolean addMusic(Music music)
    {
        return addMusic(music, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean addMusic(Music music, java.util.Map<String, String> context)
    {
        return _iceI_addMusicAsync(music, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> addMusicAsync(Music music)
    {
        return _iceI_addMusicAsync(music, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> addMusicAsync(Music music, java.util.Map<String, String> context)
    {
        return _iceI_addMusicAsync(music, context, false);
    }

    /**
     * @hidden
     * @param iceP_music -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_addMusicAsync(Music iceP_music, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "addMusic", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     Music.ice_write(ostr, iceP_music);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default boolean removeMusic(String title, String artist)
    {
        return removeMusic(title, artist, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean removeMusic(String title, String artist, java.util.Map<String, String> context)
    {
        return _iceI_removeMusicAsync(title, artist, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> removeMusicAsync(String title, String artist)
    {
        return _iceI_removeMusicAsync(title, artist, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> removeMusicAsync(String title, String artist, java.util.Map<String, String> context)
    {
        return _iceI_removeMusicAsync(title, artist, context, false);
    }

    /**
     * @hidden
     * @param iceP_title -
     * @param iceP_artist -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_removeMusicAsync(String iceP_title, String iceP_artist, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "removeMusic", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_title);
                     ostr.writeString(iceP_artist);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default boolean modifyMusic(Music music, String title, String artist)
    {
        return modifyMusic(music, title, artist, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean modifyMusic(Music music, String title, String artist, java.util.Map<String, String> context)
    {
        return _iceI_modifyMusicAsync(music, title, artist, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> modifyMusicAsync(Music music, String title, String artist)
    {
        return _iceI_modifyMusicAsync(music, title, artist, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> modifyMusicAsync(Music music, String title, String artist, java.util.Map<String, String> context)
    {
        return _iceI_modifyMusicAsync(music, title, artist, context, false);
    }

    /**
     * @hidden
     * @param iceP_music -
     * @param iceP_title -
     * @param iceP_artist -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_modifyMusicAsync(Music iceP_music, String iceP_title, String iceP_artist, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "modifyMusic", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     Music.ice_write(ostr, iceP_music);
                     ostr.writeString(iceP_title);
                     ostr.writeString(iceP_artist);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default String getMusicStream(Music music)
    {
        return getMusicStream(music, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String getMusicStream(Music music, java.util.Map<String, String> context)
    {
        return _iceI_getMusicStreamAsync(music, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getMusicStreamAsync(Music music)
    {
        return _iceI_getMusicStreamAsync(music, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getMusicStreamAsync(Music music, java.util.Map<String, String> context)
    {
        return _iceI_getMusicStreamAsync(music, context, false);
    }

    /**
     * @hidden
     * @param iceP_music -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_getMusicStreamAsync(Music iceP_music, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getMusicStream", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     Music.ice_write(ostr, iceP_music);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default void getMusicList()
    {
        getMusicList(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void getMusicList(java.util.Map<String, String> context)
    {
        _iceI_getMusicListAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> getMusicListAsync()
    {
        return _iceI_getMusicListAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> getMusicListAsync(java.util.Map<String, String> context)
    {
        return _iceI_getMusicListAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_getMusicListAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getMusicList", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default boolean uploadMusic(String title, String artist, String path)
    {
        return uploadMusic(title, artist, path, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean uploadMusic(String title, String artist, String path, java.util.Map<String, String> context)
    {
        return _iceI_uploadMusicAsync(title, artist, path, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> uploadMusicAsync(String title, String artist, String path)
    {
        return _iceI_uploadMusicAsync(title, artist, path, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> uploadMusicAsync(String title, String artist, String path, java.util.Map<String, String> context)
    {
        return _iceI_uploadMusicAsync(title, artist, path, context, false);
    }

    /**
     * @hidden
     * @param iceP_title -
     * @param iceP_artist -
     * @param iceP_path -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_uploadMusicAsync(String iceP_title, String iceP_artist, String iceP_path, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "uploadMusic", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_title);
                     ostr.writeString(iceP_artist);
                     ostr.writeString(iceP_path);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default Music[] getMusics()
    {
        return getMusics(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default Music[] getMusics(java.util.Map<String, String> context)
    {
        return _iceI_getMusicsAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Music[]> getMusicsAsync()
    {
        return _iceI_getMusicsAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Music[]> getMusicsAsync(java.util.Map<String, String> context)
    {
        return _iceI_getMusicsAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Music[]> _iceI_getMusicsAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Music[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getMusics", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     Music[] ret;
                     ret = arrayMusicHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusicServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), MusicServerPrx.class, _MusicServerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusicServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), MusicServerPrx.class, _MusicServerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusicServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), MusicServerPrx.class, _MusicServerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusicServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), MusicServerPrx.class, _MusicServerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static MusicServerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, MusicServerPrx.class, _MusicServerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static MusicServerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, MusicServerPrx.class, _MusicServerPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default MusicServerPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (MusicServerPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default MusicServerPrx ice_adapterId(String newAdapterId)
    {
        return (MusicServerPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default MusicServerPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (MusicServerPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default MusicServerPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (MusicServerPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default MusicServerPrx ice_invocationTimeout(int newTimeout)
    {
        return (MusicServerPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default MusicServerPrx ice_connectionCached(boolean newCache)
    {
        return (MusicServerPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default MusicServerPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (MusicServerPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default MusicServerPrx ice_secure(boolean b)
    {
        return (MusicServerPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default MusicServerPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (MusicServerPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default MusicServerPrx ice_preferSecure(boolean b)
    {
        return (MusicServerPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default MusicServerPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (MusicServerPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default MusicServerPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (MusicServerPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default MusicServerPrx ice_collocationOptimized(boolean b)
    {
        return (MusicServerPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default MusicServerPrx ice_twoway()
    {
        return (MusicServerPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default MusicServerPrx ice_oneway()
    {
        return (MusicServerPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default MusicServerPrx ice_batchOneway()
    {
        return (MusicServerPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default MusicServerPrx ice_datagram()
    {
        return (MusicServerPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default MusicServerPrx ice_batchDatagram()
    {
        return (MusicServerPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default MusicServerPrx ice_compress(boolean co)
    {
        return (MusicServerPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default MusicServerPrx ice_timeout(int t)
    {
        return (MusicServerPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default MusicServerPrx ice_connectionId(String connectionId)
    {
        return (MusicServerPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default MusicServerPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (MusicServerPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::MusicManager::MusicServer";
    }
}
