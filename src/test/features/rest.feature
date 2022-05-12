#language: ru
@api
Функционал: RequestSteps



  Сценарий: 1
    И выполнен запрос POST на URL "https://petstore.swagger.io/v2/pet" с параметрами из таблицы, ответ сохранен в переменную "response"
| HEADER | accept       | application/json |
| HEADER | Content-Type | application/json |
| BODY   | json         | json.post.pet    |
    Тогда в ответе "response" statusCode: 200

