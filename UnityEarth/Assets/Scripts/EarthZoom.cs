using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/**
 * EarthScale class to handle the zoom of the Earth from the Nativ Android application.
 */
public class EarthZoom : MonoBehaviour
{
	// FOR DATA
	const float initialPositionX = -0.095f;
	const float initialPositionY = 0.0f;
	const float initialPositionZ = 0.8f;
	private Vector3 startPosition = new Vector3(initialPositionX, initialPositionY, initialPositionZ);
	private Vector3 positionChange;
	
	// FOR COMMUNICATION
	private AndroidJavaObject communicationBridge;
	
	// -----------------
	// BASE FUNCTIONS
	// -----------------

	// Start is called before the first frame update
	private void Start()
	{
		positionChange = startPosition;
		// Instanciate the object for the communication with the android software
		communicationBridge = new AndroidJavaObject(
			"org.desperu.nativandroidearth.bridge.EarthBridgeImpl");
	}

	// Update is called once per frame
    void Update()
    {
        gameObject.transform.position = positionChange;
    }

	// -----------------
	// COMMUNICATION
	// -----------------

	/**
	 * To reset the scale value from Android call.
	 */
	public void ResetScale()
	{
		Debug.Log("Coming from Android: resetScale");

        positionChange = startPosition;
	}

	/**
	 * To update the scale value from Android call.
	 */
	public void UpdateScale(string zoom)
	{
		Debug.Log($"Coming from Android: updateScale {zoom}");

		positionChange = new Vector3(initialPositionX, initialPositionY, int.Parse(zoom));
		communicationBridge.Call("displayZoom", zoom);
	}
}
