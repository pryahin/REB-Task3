# РЕБ. Задача 3

## Условие
Дан массив Shares(Owner String, Issuer String, Percentage Float) владения компаниями акциями друг друга. В массиве три 
поля: наименование компании-владельца акций, наименование компании-эмитента акций, процентная доля владения. Компании 
могут владеть акциями иерархически (компания родитель владеет дочерними компаниями, а через них их дочерними и т.д.), 
а также взаимно владеть акциями друг друга. Если компания А владеет акциями Б, а Б владеет акциями В, то А владеет 
долей Б в компании В, если доля А в Б больше 50%. Доли владения можно суммировать.

## Задание
Необходимо написать программу, которая выведет все компании, у которых есть владелец контрольного пакета 
(непосредственный или через другие компании), имеющий более 50% акций этой компании, и указать всех таких владельцев. 

## Сборка контейнера
Контейнер будет собираться и запускаться командами вида:
```
docker build -t [USERNAME] https://github.com/pryahin/Technopark-Database.git
docker run -p 5000:5000 --name [CONTAINER_NAME] -t [USERNAME]
```

## Запуск
После сборки контейнера Spring запустится на 5000 порту.
 
POST запросом на `/api/get` отправляем массив в формате JSON
```json
[
   {
      "Owner":"ООО Материк",
      "Issuer":"ООО Остров",
      "Percentage":65.050
   },
   {
      "Owner":"ООО Материк",
      "Issuer":"ООО Горы",
      "Percentage":54.009
   },
   {
      "Owner":"ООО Горы",
      "Issuer":"ООО Лес",
      "Percentage":17.120
   },
   {
      "Owner":"ООО Остров",
      "Issuer":"ООО Лес",
      "Percentage":40.334
   },
   {
      "Owner":"ООО Лес",
      "Issuer":"ООО Материк",
      "Percentage":1.002
   }
]
```