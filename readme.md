# synapses-java

A **neural networks** library for **Java**!

## Basic usage

### Install synapses-java

```
<!-- https://mvnrepository.com/artifact/com.github.mrdimosthenis/synapses-java -->
<dependency>
    <groupId>com.github.mrdimosthenis</groupId>
    <artifactId>synapses-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Import the `Net` class

```java
import com.github.mrdimosthenis.synapses.Fun;
```

### Create a random neural network by providing its layer sizes

```java
Net randNet = new Net(new int[]{2, 3, 1});
```

* Input layer: the first layer of the network has 2 nodes.
* Hidden layer: the second layer has 3 neurons.
* Output layer: the third layer has 1 neuron.

### Get the json of the random neural network

```java
randNet.json();
// """
// [[{"activationF" : "sigmoid", "weights" : [-0.5,0.1,0.8]},
//   {"activationF" : "sigmoid", "weights" : [0.7,0.6,-0.1]},
//   {"activationF" : "sigmoid", "weights" : [-0.8,-0.1,-0.7]}],
//  [{"activationF" : "sigmoid", "weights" : [0.5,-0.3,-0.4,-0.5]}]]
// """
```

### Create a neural network by providing its json

```java
Net net = new Net(
    """
    [[{"activationF" : "sigmoid", "weights" : [-0.5,0.1,0.8]},
      {"activationF" : "sigmoid", "weights" : [0.7,0.6,-0.1]},
      {"activationF" : "sigmoid", "weights" : [-0.8,-0.1,-0.7]}],
     [{"activationF" : "sigmoid", "weights" : [0.5,-0.3,-0.4,-0.5]}]]
    """
);
```

### Make a prediction

```java
net.predict(new double[]{0.2, 0.6});
// [0.49131100324012494]
```

### Train a neural network

```java
net.fit(0.1, new double[]{0.2, 0.6}, new double[]{0.9});
```

The `fit` method adjusts the weights of the neural network to a single observation.

In practice, for a neural network to be fully trained, it should be fitted with multiple observations.

## Advanced usage

### Import the rest of the classes

```java
import com.github.mrdimosthenis.synapses.Attribute;
import com.github.mrdimosthenis.synapses.Codec;
import com.github.mrdimosthenis.synapses.Fun;
import com.github.mrdimosthenis.synapses.Stats;
```

### Boost the performance

Every function is efficient because its implementation is based on lazy list
and all information is obtained at a single pass.

For a neural network that has huge layers, the performance can be further improved
by using the parallel counterparts of `predict` and `fit` (`parPredict` and `parFit`).

### Create a neural network for testing

```java
new Net(new int[]{2, 3, 1}, 1000L);
```

We can provide a `seed` to create a non-random neural network.
This way, we can use it for testing.

### Define the activation functions and the weights

```java
IntFunction<Fun> activationF = layerIndex ->
        switch (layerIndex) {
            case 0 -> Fun.IDENTITY;
            case 1 -> Fun.SIGMOID;
            case 2 -> Fun.LEAKY_RE_LU;
            default -> Fun.TANH;
        };

IntFunction<Double> weightInitF = _layerIndex ->
        1.0 - 2.0 * new Random().nextDouble();

Net customNet = new Net(new int[]{4, 6, 8, 5, 3}, activationF, weightInitF);
```

* The `activationF` function accepts the index of a layer and returns an activation function for its neurons.
* The `weightInitF` function accepts the index of a layer and returns a weight for the synapses of its neurons.

If we don't provide these functions, the activation function of all neurons is sigmoid,
and the weight distribution of the synapses is normal between -1.0 and 1.0.

### Draw a neural network

```java
customNet.svg();
```

![Network Drawing](https://github.com/mrdimosthenis/synapses-java/blob/master/neural_network.png?raw=true)

With its svg drawing, we can see what a neural network looks like.
The color of each neuron depends on its activation function
while the transparency of the synapses depends on their weight.

### Measure the difference between the expected and predicted values

```java
Supplier<Stream<double[][]>> expAndPredVals = () -> Arrays.stream(
        new double[][][]{
            {{0.0, 0.0, 1.0}, {0.0, 0.1, 0.9}},
            {{0.0, 1.0, 0.0}, {0.8, 0.2, 0.0}},
            {{1.0, 0.0, 0.0}, {0.7, 0.1, 0.2}},
            {{1.0, 0.0, 0.0}, {0.3, 0.3, 0.4}},
            {{0.0, 0.0, 1.0}, {0.2, 0.2, 0.6}}
        }
);
```

* Root-mean-square error

```java
Stats.rmse(expAndPredVals.get());
// 0.6957010852370435
```

* Classification accuracy score

```java
Stats.score(expAndPredVals.get());
// 0.6
```

### Create a `Codec` by providing the attributes and the data points

* One hot encoding is a process that turns discrete attributes into a list of 0.0 and 1.0.
* Minmax normalization scales continuous attributes into values between 0.0 and 1.0.

You can use a `Codec` to encode and decode a data point.

```java
Map<String, String> setosa = Map.of(
    "petal_length", "1.5",
    "petal_width", "0.1",
    "sepal_length", "4.9",
    "sepal_width", "3.1",
    "species", "setosa"
);

Map<String, String> versicolor = Map.of(
    "petal_length", "3.8",
    "petal_width", "1.1",
    "sepal_length", "5.5",
    "sepal_width", "2.4",
    "species", "versicolor"
);

Map<String, String> virginica = Map.of(
    "petal_length", "6.0",
    "petal_width", "2.2",
    "sepal_length", "5.0",
    "sepal_width", "1.5",
    "species", "virginica"
);

Stream dataset = Arrays.stream(
    new Map[]{setosa, versicolor, virginica}
);

Attribute[] attributes = {
    new Attribute("petal_length", false),
    new Attribute("petal_width", false),
    new Attribute("sepal_length", false),
    new Attribute("sepal_width", false),
    new Attribute("species", true)
};

Codec codec = new Codec(attributes, dataset);
```

* The first parameter is a list of pairs that define the name and the type (discrete or not) of each attribute.
* The second parameter is an iterator that contains the data points.

### Get the json of the codec

```java
String codecJson = codec.json();
// codecJson: String = """[
//   {"Case" : "SerializableContinuous",
//    "Fields" : [{"key" : "petal_length","min" : 1.5,"max" : 6.0}]},
//   {"Case" : "SerializableContinuous",
//    "Fields" : [{"key" : "petal_width","min" : 0.1,"max" : 2.2}]},
//   {"Case" : "SerializableContinuous",
//    "Fields" : [{"key" : "sepal_length","min" : 4.9,"max" : 5.5}]},
//   {"Case" : "SerializableContinuous",
//    "Fields" : [{"key" : "sepal_width","min" : 1.5,"max" : 3.1}]},
//   {"Case" : "SerializableDiscrete",
//    "Fields" : [{"key" : "species","values" : ["virginica","versicolor","setosa"]}]}
// ]"""
```

### Create a codec by providing its json

```java
new Codec(codecJson);
```

### Encode a data point

```java
double[] encodedSetosa = codec.encode(setosa);
// [0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0]
```

### Decode a data point

```java
codec.decode(encodedSetosa);
// {species=setosa, sepal_width=3.1, petal_width=0.1, petal_length=1.5, sepal_length=4.9}
```
