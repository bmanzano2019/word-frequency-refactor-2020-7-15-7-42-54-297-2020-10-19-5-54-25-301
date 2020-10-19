import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SINGLE_SPACE = " ";
    private static final String NEWLINE = "\n";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputStr){
        if (inputStr.split(WHITESPACE_REGEX).length==1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(WHITESPACE_REGEX);

                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfoList.add(wordInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(wordInfoList);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }
                wordInfoList = list;

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE);
                for (WordInfo w : wordInfoList) {
                    String s = w.getValue() + SINGLE_SPACE +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> mapWordValueWordInfo = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!mapWordValueWordInfo.containsKey(wordInfo.getValue())){
                ArrayList arrayWordInfo = new ArrayList<>();
                arrayWordInfo.add(wordInfo);
                mapWordValueWordInfo.put(wordInfo.getValue(), arrayWordInfo);
            }
            else {
                mapWordValueWordInfo.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return mapWordValueWordInfo;
    }

}
