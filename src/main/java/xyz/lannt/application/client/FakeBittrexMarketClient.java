package xyz.lannt.application.client;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import xyz.lannt.exception.MarketClientException;
import xyz.lannt.market.client.BittrexMarketClient;
import xyz.lannt.market.client.MarketClientSetting;
import xyz.lannt.market.request.MarketRequest;

public class FakeBittrexMarketClient extends BittrexMarketClient {

  public FakeBittrexMarketClient(MarketClientSetting setting) {
    super(setting);
  }

  private String getJson(String fileName) {
    try {
      File file = new ClassPathResource(fileName).getFile();
      return FileUtils.readFileToString(file);
    } catch (IOException e) {
      throw new MarketClientException(e);
    }
  }

  @Override
  public String request(String uri, MarketRequest request) {
    String fileName = "";
    switch(uri) {
      case "account/getbalances":
        fileName = "FakeBittrexMarketClient_getBalances.json";
        break;
      case "public/getmarketsummaries":
        fileName = "FakeBittrexMarketClient_getMarketSummaries.json";
        break;
      case "account/getorderhistory":
        fileName = "FakeBittrexMarketClient_getOrderHistory.json";
        break;
      case "market/selllimit":
        fileName = "FakeBittrexMarketClient_sell.json";
        break;
      default:
        fileName = "";
        break;
    }
    return getJson(fileName);
  }
}
