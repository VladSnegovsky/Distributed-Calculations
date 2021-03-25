package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func printArray(line *[60]string) string {
	answer := ""
	for i := 0; i < 60; i++ {
		answer = answer + line[i] + " "
	}
	return answer
}

func leftPart(waitGroup *sync.WaitGroup, line *[60]string) {
	defer waitGroup.Done()
	for i := 58; i >= 0; i-- {
		time.Sleep(time.Second/2)
		if line[i+1] == "Left" && line[i] == "Right" {
			line[i] = "Left"
		}
		fmt.Println("LEFT:" + printArray(line))
	}

	fmt.Println("LEFT IS DONE")
}

func rightPart(waitGroup *sync.WaitGroup, line *[60]string) {
	defer waitGroup.Done()
	for i := 1; i < 60; i++ {
		time.Sleep(time.Second/2)
		if line[i-1] == "Right" && line[i] == "Left" {
			line[i] = "Right"
		}
		fmt.Println("RIGHT:" + printArray(line))
	}

	time.Sleep(10*time.Second)
	fmt.Println("RIGHT IS DONE")
}

func createLine() [60]string {
	var res [60]string
	for i := 0; i < 60; i++ {
		pos := rand.Intn(2)
		if pos == 1 {
			res[i] = "Left"
		} else if pos == 0 {
			res[i] = "Right"
		}
	}
	return res
}

func main() {
	lineLeft := createLine()
	lineRight := createLine()
	fmt.Println(lineLeft)
	fmt.Println(lineRight)
	//lineLeft[59] = "Right"
	//lineRight[0] = "Left"
	for {
		fmt.Println("New Attempt")
		var waitGroup sync.WaitGroup

		waitGroup.Add(1)
		go leftPart(&waitGroup, &lineLeft)
		waitGroup.Add(1)
		go rightPart(&waitGroup, &lineRight)

		waitGroup.Wait()

		if lineLeft[59] == "Right" && lineRight[0] == "Left" {
			lineLeft[59] = "Left"
			lineRight[0] = "Right"
		} else { break }
	}
}