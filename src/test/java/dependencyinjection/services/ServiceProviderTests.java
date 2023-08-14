package dependencyinjection.services;

import com.github.mp.dependencyinjection.factory.ServiceProviderFactory;
import com.github.mp.dependencyinjection.models.Descriptor;
import com.github.mp.dependencyinjection.models.enums.Lifetime;
import com.github.mp.dependencyinjection.services.IServiceProvider;
import dependencyinjection.services.fixtures.LowerLevelClass;
import dependencyinjection.services.fixtures.MainTestClass;
import dependencyinjection.services.fixtures.WrapperForTests;
import dependencyinjection.services.fixtures.WrapperForTests2;
import examples.ex1.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ServiceProviderTests {
    private IServiceProvider sut;
    private Map<Type, Descriptor> descriptors;

    @BeforeEach
    public void setUp() {
        descriptors = new HashMap<>();
        sut = ServiceProviderFactory.createWithDotNetLifeTimes(descriptors);
    }

    @Test
    public void getService_whenAddedAsSingleton_shouldAlwaysGiveTheSameInstance() {
        // Arrange
        descriptors.put(Object.class, new Descriptor(Lifetime.SINGLETON, Object.class));

        // Act
        var result = sut.getService(Object.class);
        var result2 = sut.getService(Object.class);

        // Assert
        Assertions.assertEquals(result2, result);
    }

    @Test
    public void getService_whenAddedAsScopedButGetServiceIsCalledTwice_shouldNotGiveTheSameInstance() {
        // Arrange
        descriptors.put(Object.class, new Descriptor(Lifetime.SCOPED, Object.class));

        // Act
        var result = sut.getService(Object.class);
        var result2 = sut.getService(Object.class);

        // Assert
        Assertions.assertNotEquals(result2, result);
    }

    @Test
    public void getService_whenAddedAsTransientButGetServiceIsCalledTwice_shouldNotGiveTheSameInstance() {
        // Arrange
        descriptors.put(Object.class, new Descriptor(Lifetime.TRANSIENT, Object.class));

        // Act
        var result = sut.getService(Object.class);
        var result2 = sut.getService(Object.class);

        // Assert
        Assertions.assertNotEquals(result2, result);
    }

    @Test
    public void getService_whenAddedAsScoped_shouldGiveTheSameInstanceWithinOneGetServiceCall() {
        // Arrange
        descriptors.put(LowerLevelClass.class, new Descriptor(Lifetime.SCOPED, LowerLevelClass.class));
        descriptors.put(WrapperForTests.class, new Descriptor(Lifetime.TRANSIENT, WrapperForTests.class));
        descriptors.put(WrapperForTests2.class, new Descriptor(Lifetime.TRANSIENT, WrapperForTests2.class));
        descriptors.put(MainTestClass.class, new Descriptor(Lifetime.TRANSIENT, MainTestClass.class));

        // Act
        var result = sut.getService(MainTestClass.class);
        var result2 = sut.getService(MainTestClass.class);

        // Assert
        Assertions.assertEquals(result.wrapper.obj, result.wrapper2.obj);
        Assertions.assertEquals(result2.wrapper.obj, result2.wrapper2.obj);
        Assertions.assertNotEquals(result.wrapper.obj, result2.wrapper2.obj);
        Assertions.assertNotEquals(result2.wrapper.obj, result.wrapper2.obj);
    }

    @Test
    public void getService_whenAddedAsSingleton_shouldGiveTheSameInstance() {
        // Arrange
        descriptors.put(LowerLevelClass.class, new Descriptor(Lifetime.SINGLETON, LowerLevelClass.class));
        descriptors.put(WrapperForTests.class, new Descriptor(Lifetime.TRANSIENT, WrapperForTests.class));
        descriptors.put(WrapperForTests2.class, new Descriptor(Lifetime.TRANSIENT, WrapperForTests2.class));
        descriptors.put(MainTestClass.class, new Descriptor(Lifetime.TRANSIENT, MainTestClass.class));

        // Act
        var result = sut.getService(MainTestClass.class);
        var result2 = sut.getService(MainTestClass.class);

        // Assert
        Assertions.assertEquals(result.wrapper.obj, result.wrapper2.obj);
        Assertions.assertEquals(result2.wrapper.obj, result2.wrapper2.obj);
        Assertions.assertEquals(result.wrapper.obj, result2.wrapper2.obj);
        Assertions.assertEquals(result2.wrapper.obj, result.wrapper2.obj);
    }

    @Test
    public void getService_whenAddedAsTransient_shouldNotGiveTheSameInstanceWithinOneGetServiceCall() {
        // Arrange
        descriptors.put(LowerLevelClass.class, new Descriptor(Lifetime.TRANSIENT, LowerLevelClass.class));
        descriptors.put(WrapperForTests.class, new Descriptor(Lifetime.TRANSIENT, WrapperForTests.class));
        descriptors.put(WrapperForTests2.class, new Descriptor(Lifetime.TRANSIENT, WrapperForTests2.class));
        descriptors.put(MainTestClass.class, new Descriptor(Lifetime.TRANSIENT, MainTestClass.class));

        // Act
        var result = sut.getService(MainTestClass.class);
        var result2 = sut.getService(MainTestClass.class);

        // Assert
        Assertions.assertNotEquals(result.wrapper.obj, result.wrapper2.obj);
        Assertions.assertNotEquals(result2.wrapper.obj, result2.wrapper2.obj);
        Assertions.assertNotEquals(result.wrapper.obj, result2.wrapper2.obj);
        Assertions.assertNotEquals(result2.wrapper.obj, result.wrapper2.obj);
    }
}
