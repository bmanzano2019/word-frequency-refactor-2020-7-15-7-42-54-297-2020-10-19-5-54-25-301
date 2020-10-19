import java.util.*;

public class WordFrequencyGame {
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String NEWLINE = "\n";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputStr){
        if (inputStr.split(WHITESPACE_REGEX).length==1) {
            return String.format("%s %d", inputStr, 1);
        } else {
            try {
                List<WordInfo> wordInfoList = listPerWordCount(inputStr);

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner combinedLineOutput = new StringJoiner(NEWLINE);
                for (WordInfo wordInfo : wordInfoList) {
                    combinedLineOutput.add(String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount()));
                }
                return combinedLineOutput.toString();
            } catch (Exception calculateError) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private List<WordInfo> listPerWordCount(String inputStr) {
        List<String> words = Arrays.asList(inputStr.split(WHITESPACE_REGEX));

        List<WordInfo> wordInfoList = new ArrayList<>();

        for (String word : new HashSet<>(words)) {
            int count = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, count));
        }

        return wordInfoList;
    }

}
