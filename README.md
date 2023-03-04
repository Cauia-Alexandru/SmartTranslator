# SmartTranslator

## Description
The project is about a similar application to Google Translate that can translate
words or sentences from one language to another. The project provides multiple 
translation options (synonyms) as well.

## Implementation

- Reading from dictionaries and saving the information in a single data collection. 
For reading from JSON, I used the gson library (https://github.com/google/gson).


- Method for adding a word to the dictionary.
  - boolean addWord(Word word, String language)
  - Returns true if the word was added to the dictionary or false if the word
  already exists in the dictionary.
  

- Method for deleting a word from the dictionary that takes the word and language as parameters.
  * boolean removeWord(String word, String language)
  * Returns true if the word was deleted from the dictionary or false if the word does not exist in the dictionary.
  

- Method for adding a new definition for a word given as a parameter.
  * boolean addDefinitionForWord(String word, String language, Definition definition)
  * Returns true if the definition was added or false if there is already a definition from the same dictionary.


- Method for deleting a definition of a word given as a parameter.
  * boolean removeDefinition(String word, String language, String dictionary)
  * Returns true if the definition was deleted or false if there is no definition from the given dictionary.


- Method for translating a word.
  * String translateWord(String word, String fromLanguage, String toLanguage)
  * Returns the translation of the word from the fromLanguage to the toLanguage.


- Method for translating a sentence.
  * String translateSentence(String sentence, String fromLanguage, String toLanguage)
  * Returns the translation of the sentence from the fromLanguage to the toLanguage.


- Method for translating a sentence and providing 3 translation options using synonyms of the words.
  * ArrayList<String> translateSentences(String sentence, String fromLanguage, String
    toLanguage)
  * Returns the translation of the sentence from the fromLanguage to the toLanguage, along with up to 3 translation options using synonyms of the words. 
  * If there are fewer than 3 possible translation options, only those options will be provided.
  

- Method for returning the definitions and synonyms of a word.
  * ArrayList<Definition> getDefinitionsForWord(String word, String language)
  * The definitions are sorted in ascending order by the year of publication of the dictionary.


- Method for exporting a dictionary in JSON format. 
  * void exportDictionary(String language)
  * I have exported only the part of the data structure that pertains to the language received as a parameter and wrote the information to a file.
  * The words in the JSON are ordered alphabetically, and the definitions are ordered by the year of publication of the dictionary.
  * The JSON format contains all the information about a word.
