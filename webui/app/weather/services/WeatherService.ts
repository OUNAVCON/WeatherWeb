
class WeatherService 
{

  static $inject = ['$http', 'BASE_URL'];
  httpService: ng.IHttpService;
  handlerUrl: string;
 
  constructor( $http: ng.IHttpService, private BASE_URL )
  {
    this.httpService = $http;
  }
 
  useGetWeather( params: any ): ng.IPromise< any >
  {
    var result: ng.IPromise< any > = this.httpService.get('http://localhost:9050/weather' + this.handlerUrl, params )
      .then( ( response: any ): ng.IPromise< any > => this.handlerResponded( response, params ) );
    return result;
  }
 
  handlerResponded( response: any, params: any ): any
  {
    response.data.requestParams = params;
    return response.data;
  }
 
  public getMessage() : string {
        return "Hello, world";
    }
} // HttpHandlerService class

export = WeatherService;

