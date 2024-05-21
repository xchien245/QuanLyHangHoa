package presentation;

import java.util.List;
import domain.HangHoa;

public interface Subscriber {
    void update(List<HangHoa> hanghoaList);
    void update(String[] string);
}
