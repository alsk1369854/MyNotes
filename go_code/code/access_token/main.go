package main

import (
	"crypto/hmac"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"go_access_token/myJWT"

	"github.com/joho/godotenv"
)

func init() {
	godotenv.Load()
}

func main() {
	// scanner := bufio.NewScanner(os.Stdin)
	// PASSWORD_SALT := os.Getenv("PUBLICK_KEY")

	// fmt.Print("Input Pass: ")
	// scanner.Scan()
	// input := scanner.Text()
	// InputPassHash := EncodeSha256(input, PASSWORD_SALT)
	// fmt.Printf("Input PassHash: %s\n", InputPassHash)

	// expectPassHash := "2d209d69fd301441e8729b1dd9e8f0d1e0c91a6d5fd65677a40ba1c4f4abef8a"
	// passCheck := CheckPassword(input, expectPassHash, PASSWORD_SALT)
	// fmt.Printf("Hash Code is equal: %v\n", passCheck)

	// println(getECDSA())

	// PUBLIC_KEY := os.Getenv("PUBLIC_KEY")

	PRIVATE_KEY := myJWT.GetPrivateKey()
	privateKey, err := myJWT.PEMKeyToPrivateKey(PRIVATE_KEY)
	if err != nil {
		fmt.Println("金鑰解析失敗:", err)
		return
	}
	// token := jwt.NewWithClaims(jwt.SigningMethodES256,
	// 	jwt.MapClaims{
	// 		"iss": "my-auth-server",
	// 		"sub": "john",
	// 		"foo": 2,
	// 	})

	// s, err := token.SignedString(privateKey)
	// if err != nil {
	// 	fmt.Println(err.Error())
	// } else {
	// 	fmt.Println(s)
	// }

	myToken := "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJmb28iOjIsImlzcyI6Im15LWF1dGgtc2VydmVyIiwic3ViIjoiam9obiJ9.NGwhT7YTMosdmkP4HAMJwbcfPQP7VIX_MLV79zXyBD41say69At1kvj4LNg_o5W7haXNvkyASnh_CJR6iCMw-Q"
	publicKey := &privateKey.PublicKey

	// 解析並驗證 token
	claims, err := myJWT.ParseAndVerifyToken(myToken, publicKey)
	if err != nil {
		fmt.Println("Token 驗證失敗:", err)
		return
	}

	// 獲取攜帶的訊息
	iss := claims["iss"]
	sub := claims["sub"]
	foo := claims["foo"]

	fmt.Println("iss:", iss)
	fmt.Println("sub:", sub)
	fmt.Println("foo:", foo)
}

func CheckPassword(inputPass string, expectPassHashCode string, key string) bool {
	inputPassHashCode := EncodeSha256(inputPass, key)
	return hmac.Equal([]byte(inputPassHashCode), []byte(expectPassHashCode))
}

func EncodeSha256(data string, key string) string {
	hashCode := hmac.New(sha256.New, []byte(key))
	hashCode.Write([]byte(data))
	expectHashCode := hashCode.Sum(nil)
	return hex.EncodeToString(expectHashCode)
}
