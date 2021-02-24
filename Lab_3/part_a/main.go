package main

import (
	"fmt"
	_ "fmt"
	"strconv"
	"sync"
	"time"
)

type Pot struct{
	sips int
	eating bool
}

func Bear (Semaphore chan bool, waitGroup *sync.WaitGroup, pot *Pot){
	defer waitGroup.Done()
	for {
		Semaphore <- true
		if pot.sips != 0 && pot.eating {
			pot.sips = pot.sips - 1
			fmt.Print("Bear is eating a sip.[" + strconv.Itoa(pot.sips) + "]\n")
			if pot.sips == 0 { pot.eating = false }
			time.Sleep(time.Second)
			<- Semaphore
		} else {
			<- Semaphore
			time.Sleep(time.Second)
		}
	}
}

func Bee(Semaphore chan bool, waitGroup *sync.WaitGroup, pot *Pot, i int) {
	defer waitGroup.Done()
	for {
		Semaphore <- true
		if pot.sips < 10 && pot.eating == false {
			pot.sips = pot.sips + 1
			fmt.Print("Bee {" + strconv.Itoa(i) + "} is adding a sip.[" + strconv.Itoa(pot.sips) + "]\n")
			if pot.sips == 10 { pot.eating = true }
			time.Sleep(time.Second)
			<- Semaphore
		} else {
			<- Semaphore
			time.Sleep(time.Second)
		}

	}
}

func main() {
	var waitGroup sync.WaitGroup

	var Semaphore = make(chan bool, 1)

	var num = 5
	var pot = Pot{0, false}

	for i := 0; i < num; i++{
		waitGroup.Add(1)
		go Bee(Semaphore, &waitGroup, &pot, i)
	}

	waitGroup.Add(1)
	go Bear(Semaphore, &waitGroup, &pot)

	waitGroup.Wait()
}
