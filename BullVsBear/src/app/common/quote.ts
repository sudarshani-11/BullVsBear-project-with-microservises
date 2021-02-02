export class Quote {
    exchange: string;
    shortname: string;
    quoteType: string;
    symbol: string;
    score: string;
    typeDisp: string;
    longname: string;
}
export class prices{
    currency:string
    regularMarketChange:string
    regularMarketChangePercent:string
    regularMarketPrice:string
    regularMarketDayHigh:string
    regularMarketDayRange:string
    regularMarketDayLow:string
    regularMarketPreviousClose:string
    bid:string
    ask:string
    regularMarketOpen:string
    fiftyTwoWeekRange:string
    fiftyTwoWeekLow:string
    fiftyTwoWeekHigh:string
}
export class HistoryData
{
    date:string;
    open:string;
    high:string;
    low:string;
    close:string;
    volume:string;
}
export class watchlistObj
{
    exchange:string;
    shortName:String;
    quoteType:String;
    symbol:String;
    currency:String;
    typeDisp:String;
    longName:String;
    price:String;

    constructor(value1:string,value2:string,value3:string,value4:string,value5:string,value6:string,value7:string,value8:string)
    {
            this.exchange=value1;
            this.shortName=value2;
            this.quoteType=value3;
            this.symbol=value4;
            this.currency=value5;
            this.typeDisp=value6;
            this.longName=value7;
            this.price=value8;
    }
}