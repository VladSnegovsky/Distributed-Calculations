package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"sync"
	_ "time"
)

var storage []Property
var outside []Property
var truck []Property

var storageEmpty = false
var outsideEmpty = false

var IvanovsProperty Property
var PetrovsProperty Property

var totalCost = 0

var mutexOutside = &sync.Mutex{}
var mutexTruck = &sync.Mutex{}

type Property struct {
	Name string
	Price int
}

func createPropertyList() []Property {
	var property []Property
	for i := 0; i < 20; i++ {
		property = append(property, Property{"prp_"+strconv.Itoa(i), rand.Intn(100)+100})
	}
	return property
}

type Ivanov struct{
	Name string
	hasProperty bool
	workIsDone bool
}
func (Ivanov *Ivanov) replacePropertyFromStorageToOutside(wg *sync.WaitGroup) {
	defer wg.Done()
	Ivanov.workIsDone = false
	Ivanov.hasProperty = false

	for {
		if Ivanov.hasProperty{
			workWithOutside(true, false, Ivanov.Name)
			Ivanov.hasProperty = false
		} else {
			if len(storage) == 0{
				storageEmpty = true
				Ivanov.workIsDone = true
			} else {
				workWithStorage(Ivanov.Name)
				//print("Ivanov HERE")
				Ivanov.hasProperty = true
			}
		}

		if Ivanov.workIsDone{
			break
		}
	}
}

type Petrov struct{
	Name string
	hasProperty bool
	workIsDone bool
}
func (Petrov *Petrov) replacePropertyFromOutsideToTruck(wg *sync.WaitGroup) {
	wg.Done()
	Petrov.workIsDone = false
	Petrov.hasProperty = false

	for {
		if Petrov.hasProperty {
			workWithTruck(true, false, Petrov.Name)
			Petrov.hasProperty = false
		} else {
			if len(outside) == 0 && storageEmpty == true{
				outsideEmpty = true
				Petrov.workIsDone = true
			} else if len(outside) > 0 {
				workWithOutside(false, true, Petrov.Name)
				Petrov.hasProperty = true
			}
		}

		if Petrov.workIsDone{
			break
		}
	}
}

type Nechiporuk struct{
	Name string
}
func (Nechiporuk *Nechiporuk) countProperty(wg *sync.WaitGroup) {
	wg.Done()
	for {
		if len(truck) == 0 && outsideEmpty{
			break
		} else if len(truck) > 0 {
			workWithTruck(false, true, Nechiporuk.Name)
		}
	}
}

func workWithStorage(name string) {
	IvanovsProperty = storage[len(storage)-1]
	storage = storage[:len(storage)-1]
	fmt.Println(name + " takes property [" + IvanovsProperty.Name + "] from <storage>.")
	//time.Sleep(5 * time.Second)
}
func workWithOutside(toOutside bool, fromOutside bool, name string)  {
	mutexOutside.Lock()
	fmt.Println(name + ": locks <outside>.")
	if toOutside{
		outside = append(outside, IvanovsProperty)
		fmt.Println(name + " puts property [" + IvanovsProperty.Name + "] to the <outside>.")
		//time.Sleep(5 * time.Second)
	} else if fromOutside{
		PetrovsProperty = outside[len(outside)-1]
		outside = outside[:len(outside)-1]
		fmt.Println(name + " takes property [" + PetrovsProperty.Name + "] from <outside>.")
		//time.Sleep(5 * time.Second)
	}
	fmt.Println(name + ": unlocks <outside>.")
	mutexOutside.Unlock()
}
func workWithTruck(toTruck bool, countPrice bool, name string)  {
	mutexTruck.Lock()
	fmt.Println(name + ": locks <truck>.")
	if toTruck{
		truck = append(truck, PetrovsProperty)
		fmt.Println(name + " puts property [" + IvanovsProperty.Name + "] to the <truck>.")
		//time.Sleep(5 * time.Second)
	} else if countPrice {
		var tempProperty = truck[len(truck)-1]
		truck = truck[:len(truck)-1]
		fmt.Println(name + " takes property [" + tempProperty.Name + "]. It costs " + strconv.Itoa(tempProperty.Price) + ".")
		totalCost += tempProperty.Price
		fmt.Println("Total Price is " + strconv.Itoa(totalCost) + " grn.")
		//time.Sleep(5 * time.Second)
	}
	fmt.Println(name + ": unlocks <truck>.")
	mutexTruck.Unlock()
}

func main() {
	storage = createPropertyList()

	var Ivanov = Ivanov{"Ivanov", false, false}
	var Petrov = Petrov{"Petrov", false, false}
	var Nechiporuk = Nechiporuk{"Nechiporuk"}

	var wg sync.WaitGroup
	wg.Add(3)
	go Ivanov.replacePropertyFromStorageToOutside(&wg)
	go Petrov.replacePropertyFromOutsideToTruck(&wg)
	go Nechiporuk.countProperty(&wg)
	wg.Wait()
}