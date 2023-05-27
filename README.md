<h1>
Тестовое задание (компания Kameleoon)
</h1>
</br>
<h2>
  Задача:
</h2>
Подготовить api для работы интернет-магазина. Он должен осуществлять следующие операции:
<ol>
  <li>
    Для сущности Пользователь (поля: имя, список заказов):
    <ol>
      <li>
        сохранение в таблицу
      </li>
      <li>
        поиск по id
      </li>
      <li>
        изменение
      </li>
      <li>
        удаление по id
      </li>
      <li>
        получение списка всех сущностей таблицы
      </li>
    </ol>
  </li>
  <li>
    Для сущности Товар (поля: название, артикул, цена, поле, связывающее с таблицей заказов через расшивку):
    <ol>
      <li>
        сохранение в таблицу
      </li>
      <li>
        поиск по id
      </li>
      <li>
        изменение
      </li>
      <li>
        удаление по id
      </li>
      <li>
        получение списка всех сущностей таблицы
      </li>
    </ol>
  </li>
  <li>
    Для сущности Заказ (поля: описание, клиент, поле, связывающее с таблицей товаров через расшивку)
    <ol>
      <li>
        сохранение в таблицу
      </li>
      <li>
        поиск по id
      </li>
      <li>
        изменение
      </li>
      <li>
        удаление по id
      </li>
      <li>
        получение списка всех сущностей таблицы
      </li>
      <li>
        получение информации о заказе по id
      </li>
      <li>
        получение чека по заказу по id
      </li>
    </ol>
  </li>
</ol>
</br>
<h2>Описание API</h2>
<h3>Сущность User</h3>
<table>
  <thead>
    <td>Operation</td>
    <td>Endpoint</td>
    <td>Method</td>
    <td>Parameters</td>
    <td>Response Object</td>
  </thead>
  <tr>
    <td>save</td>
    <td>"/save"</td>
    <td>Post</td>
    <td>String name, String password, String email (not required)</td>
    <td>User</td>
  </tr>
</table>
<h3>Сущность Quote</h3>
<table>
  <thead>
    <td>Operation</td>
    <td>Endpoint</td>
    <td>Method</td>
    <td>Parameters</td>
    <td>Response Object</td>
  </thead>
  <tr>
    <td>findAll</td>
    <td>"/all"</td>
    <td>Get</td>
    <td></td>
    <td>List<Quote></td>
  </tr>
  <tr>
    <td>findById</td>
    <td>"/findById"</td>
    <td>Get</td>
    <td>int id</td>
    <td>Quote</td>
  </tr>
  <tr>
    <td>save</td>
    <td>"/save"</td>
    <td>Post</td>
    <td>String content, int userId</td>
    <td>Quote</td>
  </tr>
  <tr>
    <td>update</td>
    <td>"/update"</td>
    <td>Post</td>
    <td>int quoteId, String content</td>
    <td>Quote</td>
  </tr>
  <tr>
    <td>delete</td>
    <td>"/delete"</td>
    <td>Get</td>
    <td>int id</td>
    <td>Quote</td>
  </tr>
  <tr>
    <td>top ten</td>
    <td>"/top_ten"</td>
    <td>Get</td>
    <td></td>
    <td>LinkedList<QuoteDTO></td>
  </tr>
  <tr>
    <td>worst ten</td>
    <td>"/worst_ten"</td>
    <td>Get</td>
    <td></td>
    <td>LinkedList<QuoteDTO></td>
  </tr>
  <tr>
    <td>random</td>
    <td>"/random"</td>
    <td>Get</td>
    <td></td>
    <td>Quote</td>
  </tr>
</table>
<h3>Сущность Vote (реализовано анонимное голосование)</h3>
<table>
  <thead>
    <td>Operation</td>
    <td>Endpoint</td>
    <td>Method</td>
    <td>Parameters</td>
    <td>Response Object</td>
  </thead>
  <tr>
    <td>vote</td>
    <td>"/vote"</td>
    <td>Post</td>
    <td>boolean vote, int quoteId</td>
    <td>Vote</td>
  </tr>
</table>
<p>
*В методах, где нужна более подробная информация о цитате, возвращается data transfer object на основе Quote
  для реализации графика есть поле firstVoted, хранящее дату первого голоса, и список координат, который можно
  использовать для создания графика
</p>
<p>
**Методы, возвращающие Optional<E> значения, возвращают nullObject вместо null, который можно определить по id = -1
</p>
</br>  
<h2>    
  Стек технологий:
</h2>
<lo>
  <li>Spring(Boot, Hibernate, Security)</li>
  <li>h2 database</li>
  <li>REST-api</li>
</lo>

<h3>Не выполнено:</h3>
<lo>
  <li>
    работа с Docker
  </li>
</lo>
