package myJWT

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/x509"
	"encoding/pem"
	"errors"
	"fmt"
	"log"
	"os"

	"github.com/golang-jwt/jwt"
)

func GetPrivateKey() string {
	return fmt.Sprintf(`
-----BEGIN EC PRIVATE KEY-----
%s
-----END EC PRIVATE KEY-----
`, os.Getenv("PRIVATE_KEY"))
}

func getNewPEMKey() string {
	// 產生 ECDSA 私鑰
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatal("私鑰生成失敗:", err)
	}

	pemKey, err := PrivateKeyToPEMKey(privateKey)
	if err != nil {
		log.Fatal(err.Error())
	}
	return pemKey
}

func PEMKeyToPrivateKey(pemKey string) (*ecdsa.PrivateKey, error) {
	block, _ := pem.Decode([]byte(pemKey))
	if block == nil || block.Type != "EC PRIVATE KEY" {
		return nil, errors.New("無效的金鑰格式")
	}

	privateKey, err := x509.ParseECPrivateKey(block.Bytes)
	if err != nil {
		return nil, err
	}

	return privateKey, nil
}

// PrivateKeyToPEM 將 *ecdsa.PrivateKey 轉換為 PEM 格式的金鑰字串
func PrivateKeyToPEMKey(privateKey *ecdsa.PrivateKey) (string, error) {
	// 將私鑰序列化為 DER 格式
	derKey, err := x509.MarshalECPrivateKey(privateKey)
	if err != nil {
		return "", err
	}

	// 將 DER 格式的私鑰轉換為 PEM 格式
	block := &pem.Block{
		Type:  "EC PRIVATE KEY",
		Bytes: derKey,
	}

	// 將 PEM 格式的私鑰轉換為字串
	pemKey := pem.EncodeToMemory(block)
	if pemKey == nil {
		return "", errors.New("無法編碼為 PEM 格式")
	}

	return string(pemKey), nil
}

func CreateTokenWithClaims(claims jwt.MapClaims, privateKey *ecdsa.PrivateKey) (string, error) {
	token := jwt.NewWithClaims(jwt.SigningMethodES256,
		jwt.MapClaims{
			"iss": "my-auth-server",
			"sub": "john",
			"foo": 2,
		})

	tokenString, err := token.SignedString(privateKey)
	if err != nil {
		return "", err
	}
	return tokenString, nil
}

func ParseAndVerifyToken(tokenString string, publicKey *ecdsa.PublicKey) (jwt.MapClaims, error) {
	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
		// 驗證簽名算法
		if _, ok := token.Method.(*jwt.SigningMethodECDSA); !ok {
			return nil, fmt.Errorf("無效的簽名算法：%v", token.Header["alg"])
		}

		return publicKey, nil
	})

	if err != nil {
		return nil, fmt.Errorf("token 解析失敗：%v", err)
	}

	claims, ok := token.Claims.(jwt.MapClaims)
	if !ok || !token.Valid {
		return nil, fmt.Errorf("token 驗證失敗")
	}

	return claims, nil
}
