package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({"classpath:config.properties"})
public interface Configuration extends Config {

    @Key("browser")
    @DefaultValue("chromium")
    String browser();

    @Key("headless")
    boolean headless();

    @Key("thread.count")
    @DefaultValue("1")
    int threadCount();

    @Key("navigation.timeout")
    int navigationTimeout();

    @Key("action.timeout")
    int actionTimeout();
}