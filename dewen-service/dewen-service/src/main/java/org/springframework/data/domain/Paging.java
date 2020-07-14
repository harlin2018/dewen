package org.springframework.data.domain;

import java.util.List;
import java.util.function.Function;


public class Paging {

    public static <T> Page<T> of(List<T> content, Pageable pageable, Long total){
        return new PageImpl(content, pageable, total);
    }

    public static class PageImpl<T> extends Chunk<T> implements Page<T>{

        private final Long total;

        public PageImpl(List<T> content, Pageable pageable, Long total) {
            super(content, pageable);
            this.total = total;
        }

        @Override
        public int getTotalPages() {
            return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
        }

        @Override
        public long getTotalElements() {
            return total;
        }

        @Override
        public boolean hasNext() {
            return getNumber() + 1 < getTotalPages();
        }

        @Override
        public <U> Page<U> map(Function<? super T, ? extends U> converter) {
            return new PageImpl<>(getConvertedContent(converter), getPageable(), total);
        }
    }

}
