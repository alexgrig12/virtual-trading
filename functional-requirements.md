## Functional Requirements

| Назва вимоги                                              |
| --------------------------------------------------------- |
| FR1. Аутентифікація;                                         |
| FR2. Перегляд списку активів;                               |
| FR3. Пошук активів;                                         |
| FR4. Перегляд інформації про актив;                      |
| FR5. Додавання активу в обране;                                  |
| FR6. Перегляд загального балансу коштів;                         |
| FR7. Перегляд активів користувача;                          |
| FR8. Отримання щоденних віртуальних коштів;                  |
| FR9. Купівля активу;                                       |
| FR10. Продаж активу;                                       |
| FR11. Конвертація активів;                                  |
| FR12. Повідомлення про можливість отримати щоденні кошти; |
| FR13. Налаштування повідомлень;                           |
| FR14. Перегляд історія транзакцій певного активу;         |

## Screens

|       Назва екрану        | FR які задоволняє |                    Опис                     | Назва feature-модулю до якого відноситься |
| :-----------------------: | :---------------: | :-----------------------------------------: | ----------------------------------------- |
|       Coins Screen        |  FR2, FR5, FR6,   |             Екран списку активів              | :feature:coins                            |
|    SearchCoins Screen     |        FR3        |             Екран пошуку активів              | :feature:coins                            |
|    CoinDetails Screen     |        FR4        |           Екран з деталями активів            | :feature:coins                            |
|     Dashboard Screen      |        FR7        | Екран з активами та інформацією користувача |                                           |
|        Earn Screen        |        FR8        |       Екран отримання щоденних коштів       |                                           |
|      BuyCoin Screen       |        FR9        |            Екран закупівлі активу            |                                           |
|      SellCoin Screen      |       FR10        |             Екран продажу активу             |                                           |
|    ConvertCoin Screen     |       FR11        |           Екран конвертації активів           |                                           |
|     СhooseCoin Screen     |     FR9, FR10     |   Екран вибору активу для купівлі/продажу   |                                           |
|      Profile Screen       |    FR12, FR13     |  Екран налаштування профілю користувача   |                                           |
| ApproveTransaction Screen |     FR9, FR10     |     Екран підтвердження купівлі/продажу активу     |                                           |
|  UserCoinDetails Screen   |       FR14        |  Екран з історією транзакцій певного активу  |                                           |

## Modules

```mermaid
    flowchart TB
        featureCoins[:feature:coins]

        featureDashboard[:feature:dashboard]

        featureProfile[:feature:profile]

        featureEarn[:feature:earncoin]

        featureBuy[:feature:buycoin]

        featureSell[:feature:sellcoin]

        featureConvert[:feature:convertcoin]

        featureAuth[:feature:auth]


        dataCoinranking[:data:coinranking]
        dataTrading[:data:trading]
        dataUser[:data:user]
        dataAuth[:data:auth]


        featureCoins -->  dataCoinranking
        featureDashboard --> dataUser
        featureProfile --> dataUser
        featureAuth --> dataAuth


        featureEarn --> dataTrading
        featureBuy --> dataTrading
        featureSell --> dataTrading
        featureConvert --> dataTrading


```
