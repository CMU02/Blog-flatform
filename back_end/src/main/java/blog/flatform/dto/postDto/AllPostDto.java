package blog.flatform.dto.postDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllPostDto<T> {
    private int count;
    private T postData;

    public AllPostDto(int count, T postData) {
        this.count = count;
        this.postData = postData;
    }
}
