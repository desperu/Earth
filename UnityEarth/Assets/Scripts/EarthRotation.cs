using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EarthRotation : MonoBehaviour
{

    private float speed = 40f;

	// Update is called once per frame
	private void Update() {
        gameObject.transform.Rotate(0f, speed * Time.deltaTime, 0f);
    }

	public void CallFromAndroidWithNoMessage() {
        speed = speed * 2;
    }
	
	public void CallFromAndroidWithMessage(string value) {
		speed = float.Parse(value);
        Debug.Log($"Coming from Android: {value}");
    }
}
