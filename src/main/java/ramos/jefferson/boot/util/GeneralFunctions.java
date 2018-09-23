package ramos.jefferson.boot.util;

import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GeneralFunctions {
    
    private static final String PAGEABLE_PARAMETER_PAGE = "page";
    private static final String PAGEABLE_PARAMETER_SIZE = "size";
    
    public boolean verifyParameters(Map<String, String> parameters) {
        return (parameters != null && parameters.containsKey(PAGEABLE_PARAMETER_PAGE) && parameters.containsKey(PAGEABLE_PARAMETER_SIZE));
    }

    public PageRequest createPageable(Map<String, String> parameters) throws NumberFormatException {
        int page = Integer.parseInt(parameters.get(PAGEABLE_PARAMETER_PAGE));
        int size = Integer.parseInt(parameters.get(PAGEABLE_PARAMETER_SIZE));
        return PageRequest.of(page, size);
    }

}
