package com.zpi.currencyapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

import com.zpi.datamodel.CurrencyNoteA;

public class DataDownloaderTest {

    private CurrencyNoteA usd;

    @Test
    public void shouldReturnCurrencyNoteAwithOneRateForUSDcurrency() {
        usd = DataDownloader.getDataForSingleCurrency("USD", LocalDate.of(2019, 5, 1), LocalDate.of(2019, 5, 2));

        assertThat(usd.getRates()
                      .size(),
                equalTo(1));
        assertThat(usd.getRates()
                      .get(0)
                      .getMid(),
                equalTo(3.8177));
    }

    @Test
    public void amoutOfExchangeRatesIsEqualToDiifOfDatesWithoutWeekends() {
        int startDay = 1, endDay = 31;
        usd = DataDownloader.getDataForSingleCurrency("USD", LocalDate.of(2019, 1, startDay), LocalDate.of(2019, 1, endDay));

        assertThat(usd.getRates()
                      .size(),
                equalTo(endDay - startDay - 8));
    }

    @Test
    public void shouldChcekIfRatesAreReturnedForApproporiateCurrency() {
        usd = DataDownloader.getDataForSingleCurrency("USD", LocalDate.of(2019, 5, 1), LocalDate.of(2019, 5, 2));
        assertThat(usd.getCode(), equalTo("USD"));
    }

    @Test(expected = InvalidDateException.class)
    public void shouldThrowInvalidDateExceptionIfStartDateIsEqualToEndDate() {
        usd = DataDownloader.getDataForSingleCurrency("USD", LocalDate.of(2019, 5, 1), LocalDate.of(2019, 5, 1));
    }

    @Test(expected = InvalidDateException.class)
    public void shouldThrowInvalidDateExceptionIfStartDateLaterThenEndDate() {
        usd = DataDownloader.getDataForSingleCurrency("USD", LocalDate.of(2019, 5, 5), LocalDate.of(2019, 5, 1));
    }

    @Test(expected = InvalidDateException.class)
    public void shouldThrowInvalidDateExceptionIfDateIsIncorect() {
        usd = DataDownloader.getDataForSingleCurrency("USD", LocalDate.of(2019, 2, 31), LocalDate.of(2019, 5, 1));
    }

}
