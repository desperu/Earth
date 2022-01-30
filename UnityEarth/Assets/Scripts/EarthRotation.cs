using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/**
 * EarthRotation class to handle the rotation of the Earth from the Nativ Android application.
 */
public class EarthRotation : MonoBehaviour
{
	// FOR DATA
	private float speed = 40f;
	
	// FOR COMMUNICATION
	private AndroidJavaObject communicationBridge;

	// -----------------
	// BASE FUNCTIONS
	// -----------------
	
	// Start is called before the first frame update
	private void Start()
	{
		// Instanciate the object for the communication with the android software
		communicationBridge = new AndroidJavaObject(
			"org.desperu.nativandroidearth.bridge.EarthBridgeImpl");
	}

	// Update is called once per frame
	private void Update()
	{
		float newSpeed = speed * Time.deltaTime;
		gameObject.transform.Rotate(0f, newSpeed, 0f);

		// Send data to Android
		communicationBridge.Call("displayRotation", $"{newSpeed}");
		
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

	/**
	 * To update the rotation value from Android call.
	 */
	public void UpdateRotation(string newSpeed)
	{
		speed = int.Parse(newSpeed);
	}

	/**
	 * To reset the rotation from Android call.
	 */
	public void ResetRotation()
	{
		speed = 40f;
	}
}
