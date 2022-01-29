using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EarthManager : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // si la touche espace est enfoncée
        if (Input.GetKeyDown("space"))
        {
            // on écrit une trace dans la console
            Debug.Log("la touche espace a été enfoncée");

            // this représente l'instance du script
            // this.gameObject donne accès au GameObject du script
            // activeSelf donne la visibilité du GameObject
            bool isActive = this.gameObject.activeSelf;
            if (isActive)
            {
                // si le GameObject est actif (est visible)
                Debug.Log("on cache le gameobject 'earth'");

                // on désactive la visibilité du gameObject (on le cache)
                this.gameObject.SetActive(false);
            }
            else
            {
                // si le GameObject est inactif (est invisible)
                Debug.Log("on affiche le gameobject 'earth'");

                // on active la visibilité du gameObject (on l'affiche)
                this.gameObject.SetActive(true);
            }
        }
    }
}
