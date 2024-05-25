package blog.flatform.dto.postDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllPostAndCountDto<T> {
    private int count;
    private T postData;

    public AllPostAndCountDto(int count, T postData) {
        this.count = count;
        this.postData = postData;
    }
}
