#language: ru

  @test
Функционал: Google поиск
  Сценарий: Переход к результату поиска


     И совершен переход по ссылке "https://google.com/"
     И текущая страница установлена "Главное окно"
     И в поле "Строка поиска" введено значение "How to start Selenide"
     И выполнено нажатие на кнопку "Кнопка Поиск"
     И вывести список элементов "Результаты поиска"
     И выполнено нажатие на текст "С чего начать? - Selenid" в списке "Результаты поиска"
     И URL текущей страницы равен "https://selenide.org/quick-start.html"
     И выполнена задержка 10 секунд