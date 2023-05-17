@ModelAndView - сущность, которую мы передаем например на html страницу из java, в основном с каким то атрибутом
@ModelAttribute Object object - объект, который мы передаем из html в java, например при post запросе, заполнение которого осуществляется в html документе
    th:object=${client}
@RequestParam Type something - запросить параметр - аттрибута "something" с тега html по указанному адресу для переменной параметра java метода по данному адресу
    type = "somethingType" name="something"
    * Если помеченные @RequestParam параметры, присутствует по данному адресу в html, (с таким же типом) то их данные передаются в параметр метода java