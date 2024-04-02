package main

import (
	"fmt"
	"go-utils/src/utils"
)

func main() {
	netUtils := utils.GetNetUtils()
	for _, info := range netUtils.GetIPInfo() {
		ip := info.IPNet.IP.String()
		if netUtils.IsIpv4IP(ip) {
			fmt.Println(info.Name, ip)
		}
	}
}
