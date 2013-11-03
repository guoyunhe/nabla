package org.nabla.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class Hub {
	private static final String TAG = "HUB";
	private static final UUID mUUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private String address;
	private BluetoothAdapter mBluetoothAdapter;
	private BluetoothDevice mBluetoothDevice;
	private BluetoothSocket mBluetoothSocket;
	private OutputStream outStream;
	private InputStream inStream;

	public Hub(String bluetoothaddress) {
		address = bluetoothaddress;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(address);
		try {
			mBluetoothSocket = mBluetoothDevice
					.createRfcommSocketToServiceRecord(mUUID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON CREATE: Socket creation failed.", e);
		}

		try {
			mBluetoothSocket.connect();
			Log.e(TAG, "ON CREATE: Connection established.");
		} catch (IOException e) {
			Log.e(TAG, "ON CREATE: Connection failed.");
			try {
				mBluetoothSocket.close();
			} catch (IOException e2) {
				Log.e(TAG,
						"ON CREATE: Unable to close socket during connection failure",
						e2);
			}
		}
	}

	public String input(String send) {
		// TODO send order and receive returned status
		String receive;
		byte[] msgBuffer1 = null, msgBuffer2 = null;
		msgBuffer1 = send.getBytes();

		try {
			outStream = mBluetoothSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON CREATE: Output stream creation failed.", e);
		}

		try {
			inStream = mBluetoothSocket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON CREATE: Input stream creation failed.", e);
		}

		try {
			outStream.write(msgBuffer1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON INPUT: Exception during write.", e);
		}

		try {
			inStream.read(msgBuffer2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON INPUT: Exception during read.", e);
		}
		receive = new String(msgBuffer2);
		return receive;
	}

	public void output(String send) {
		// TODO send order to driver
		byte[] msgBuffer = null;
		msgBuffer = send.getBytes();

		try {
			outStream = mBluetoothSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON CREATE: Output stream creation failed.", e);
		}

		try {
			outStream.write(msgBuffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON OUTPUT: Exception during write.", e);
			output(send);
		}

	}
}
