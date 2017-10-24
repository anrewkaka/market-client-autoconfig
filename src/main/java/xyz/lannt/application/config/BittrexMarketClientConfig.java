package xyz.lannt.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.lannt.application.client.FakeBittrexMarketClient;
import xyz.lannt.application.property.BittrexMarketProperty;
import xyz.lannt.market.client.BittrexMarketClient;
import xyz.lannt.market.client.MarketClientSetting;

@Configuration
public class BittrexMarketClientConfig {

  @Autowired
  private BittrexMarketProperty bittrexMarketProperty;

  @Bean
  public BittrexMarketClient bittrexMarketClient() {
    MarketClientSetting setting = MarketClientSetting.builder()
    .baseUrl(bittrexMarketProperty.getBaseUrl())
    .apiKey(bittrexMarketProperty.getApiKey())
    .sercretKey(bittrexMarketProperty.getSecret())
    .build();

    if (bittrexMarketProperty.isDevMode()) {
      return new FakeBittrexMarketClient(setting);
    }

    return new BittrexMarketClient(setting);
  }
}
