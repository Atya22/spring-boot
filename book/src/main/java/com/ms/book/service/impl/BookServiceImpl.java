package service.impl;

import com.ms.book.client.AliVeNinoFeignClient;
import com.ms.book.dao.repository.BookRepository;
import com.ms.book.dto.AliVeNinoClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
private final AliVeNinoFeignClient aliVeNinoFeignClient;
private final BookRepository repository;
    @Override
    public List<AliVeNinoClientDto> getAliVeNinoClinets() {
        return aliVeNinoFeignClient.getAll();
    }
}
