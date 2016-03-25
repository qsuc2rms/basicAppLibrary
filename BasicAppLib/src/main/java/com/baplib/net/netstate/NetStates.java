package com.baplib.net.netstate;

public class NetStates {

	public static final int TYPE_DISCONNECTED = -2;
	
	public static final int TYPE_NONE = -1;
	/**
	 * The Mobile data connection. When active, all data traffic will use this
	 * network type's interface by default (it has a default route)
	 */
	public static final int TYPE_MOBILE = 0;
	/**
	 * The WIFI data connection. When active, all data traffic will use this
	 * network type's interface by default (it has a default route).
	 */
	public static final int TYPE_WIFI = 1;
	/**
	 * An MMS-specific Mobile data connection. This network type may use the
	 * same network interface as {@link #TYPE_MOBILE} or it may use a different
	 * one. This is used by applications needing to talk to the carrier's
	 * Multimedia Messaging Service servers.
	 */
	public static final int TYPE_MOBILE_MMS = 2;
	/**
	 * A SUPL-specific Mobile data connection. This network type may use the
	 * same network interface as {@link #TYPE_MOBILE} or it may use a different
	 * one. This is used by applications needing to talk to the carrier's Secure
	 * User Plane Location servers for help locating the device.
	 */
	public static final int TYPE_MOBILE_SUPL = 3;
	/**
	 * A DUN-specific Mobile data connection. This network type may use the same
	 * network interface as {@link #TYPE_MOBILE} or it may use a different one.
	 * This is sometimes by the system when setting up an upstream connection
	 * for tethering so that the carrier is aware of DUN traffic.
	 */
	public static final int TYPE_MOBILE_DUN = 4;
	/**
	 * A High Priority Mobile data connection. This network type uses the same
	 * network interface as {@link #TYPE_MOBILE} but the routing setup is
	 * different. Only requesting processes will have access to the Mobile DNS
	 * servers and only IP's explicitly requested via
	 * {@link #requestRouteToHost} will route over this interface if no default
	 * route exists.
	 */
	public static final int TYPE_MOBILE_HIPRI = 5;
	/**
	 * The WiMAX data connection. When active, all data traffic will use this
	 * network type's interface by default (it has a default route).
	 */
	public static final int TYPE_WIMAX = 6;

	/**
	 * The Bluetooth data connection. When active, all data traffic will use
	 * this network type's interface by default (it has a default route).
	 */
	public static final int TYPE_BLUETOOTH = 7;

	/**
	 * Dummy data connection. This should not be used on shipping devices.
	 */
	public static final int TYPE_DUMMY = 8;

	/**
	 * The Ethernet data connection. When active, all data traffic will use this
	 * network type's interface by default (it has a default route).
	 */
	public static final int TYPE_ETHERNET = 9;
}
