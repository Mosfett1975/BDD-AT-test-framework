#language: ru
@api
Функционал: RequestSteps



  Сценарий: 1
    И выполнен запрос POST на URL "https://petstore.swagger.io/v2/pet" с параметрами из таблицы, ответ сохранен в переменную "response"
| HEADER | accept       | application/json |
| HEADER | Content-Type | application/json |
| BODY   | json         | json.post.pet    |
    Тогда в ответе "response" statusCode: 200

    И выполнен запрос GET на URL "url.pet.petId" с параметрами из таблицы, ответ сохранен в переменную "response"
      | HEADER         | accept | application/json |
      | PATH_PARAMETER | petId  | 100500           |
    Тогда в ответе "response" statusCode: 200

    Тогда ответ из переменной "response" соответствует условиям из таблицы
      | STATUS    | status           | == | HTTP/1.1 200 OK          |
      | STATUS    | code             | == | 200                      |
      | JSON      | category.id      | == | 100500                   |
      | JSON      | name             | == | Fido                     |


