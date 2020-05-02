package com.techprimers.stock.dbservice.resource;

import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuetesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuetesRepository quetesRepository;

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {

        List<Quote> quotes = quetesRepository.findByUserName(username);
        return getQuotesByUserName(username);;
    }

    @PostMapping(value = "/add")
    public List<String> add(@RequestBody final Quotes quotes) {
        quotes.getQuotes().stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quetesRepository.save(quote));


        return getQuotesByUserName(quotes.getUserName());
    }

    private List<String> getQuotesByUserName(@PathVariable final String username) {
        return quetesRepository.findByUserName(username)
                .stream().map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable final String username) {
        return getQuotesByUserName(username);
    }
}
