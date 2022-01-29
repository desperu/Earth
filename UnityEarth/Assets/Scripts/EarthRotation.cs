using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/**
 * EarthRotation class to handle the rotation of the Earth from the Nativ Android application.
 */
public class EarthRotation : MonoBehaviour
{
	// FOR DATA
	private AndroidJavaObject communicationBridge;
	private float speed = 40f;

	// -----------------
	// BASE FUNCTIONS
	// -----------------
	
	private void Start()
	{
		// Instanciate the object for the communication with the android software
		communicationBridge = new AndroidJavaObject(
			"com.bittreat.apps.unitycommunicationtest.CommunicationBridge");
	}

	private void Update()
	{
		gameObject.transform.Rotate(0f, speed * Time.deltaTime, 0f);
		
		if (Application.platform == RuntimePlatform.Android)
		{
			if (Input.GetKey(KeyCode.Escape))
			{
				Application.Quit();
			}
		}
	}
	
	// -----------------
	// COMMUNICATION
	// -----------------

	public void CallFromAndroidWithNoMessage()
	{
		speed = speed * 2;
	}

	public void CallFromAndroidWithMessage(string value)
	{
		Debug.Log($"Coming from Android: {value}");

		if (speed <= 81f)
		{
			communicationBridge.Call("callFromUnityWithNoParameters");
			speed = 80f;
		}
		else if (speed <= 170f)
		{
			communicationBridge.Call(
				"callFromUnityWithOneParameter",
				"Hello from Unity!");
			speed = 160f;
		}
		else if (speed <= 330f)
		{
			communicationBridge.Call(
				"callFromUnityWithTwoParameters",
				"The speed was: ",
				(int) speed);
			speed = 40f;
		}
	}
}
