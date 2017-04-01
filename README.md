# quickbrownfox

Gives a textbox and a set of alphabet letters (in this case German including äöü and ß), to allow you to brainstorm [pangrams](https://en.wikipedia.org/wiki/Pangram). There are no known (or even no possible?) pangrams using each letter precisely once, so we allow using letters more than once, and display where we transgressed. Creating these by hand is difficult, so the tool should come in handy.

![Screenshot of program](https://github.com/heinzelotto/quickbrownfox/blob/master/screenshot.png)

I found one, namely "Vor Bärenjagd zwölf xylitsüße Quarkpfläumchen!" (prior to a bear hunt twelve xylite-sweet curd-plums), exceeding the letter supply by "aeefllnrruä", which is not that great, but also not too bad: "The quick brown fox jumps over the lazy dog" has "eehooortu". However, the latter is arguably a more meaningful sentence.

### Todo

* Only accepts lower case for now, and only filters spaces, not other interpunction
* Bring the suggestion list of words that fit in the letter stock to life: It should filter some suitable words from a word list
