# MyStar2 SSMLSP
The social science machine learning software package includes commonly used prediction and classification algorithms and policy text mining algorithms.

# 0. Getting Started

Visit [here(no-china)](https://github.com/MyStar2/SSMLSP/releases) or [here(china)](http://ibiur610399.java.jkj.wang/MyStar2-SSMLSP.zip) and download the compressed package.

# 1. What's MyStar2 SSMLSP, Why is MyStar2 SSMLSP?

The project hatched from the Fifth China Internet + innovation and entrepreneurship competition. There are five members in the team, Feily Zhang、Zhenhua Liu、Xingyao Wang、Chunmiao Qiu、Yunli Xue.

SSMLSP is a social science machine learning software package that currently provides command-line operations.Classification and prediction algorithm based on Weka Library.That is to say, this project is based on Weka for secondary development, shielding complex operations at the bottom, providing a simple and clear way of interaction.

# 2. Command Line Format and Meaning of Basic Parameters.

The commands of each algorithm begin with the English initials. For supervised machine learning algorithms, the parameters are common, as follows：

+ `-p` : File path and file name, currently supports three file formats: csv, ARFF and xrff. It is strongly recommended to use CSV file format.
+ `-r` : Attributes in the dataset that need to be removed, Separate by commas. Non-negative, must be within a reasonable range, namely `[0, instances.attr.length - 1]`.
+ `-t` : Target attribute index, non-negative, must be within a reasonable range.

# 3. Currently implemented algorithms and commands.

1. (Multivariate) Linear Regression Algorithms : `lg -r ... -t ... -p ...`;
2. Regression Tree Algorithms : `rt -r ... -t ... -p ...`;
3. Decision Tree Algorithms : `dt -r ... -t ... -p ...`.

eg. `lg -r 1,2,3 -t 10 -p C:\\Users\\Administrator\\Desktop\\doc\\data.csv`;

# 4. Common Error Tips and Implications.

+ Error : -p and-t parameters cannot be empty : Target attribute index and file path name must not be empty and file name must be legitimate.
+ Error : Data file does not exist : The path must exist and the file is a real file (including the file format recognized by the program).
+ Error : Eliminate column index errors : Attributes to be removed are not numeric or cross-border.
+ Error : All data set attributes must be numeric : For linear regression algorithms, all attribute types must be numeric, otherwise the error will be reported.
+ Error : target index crossing boundaries or other reasons : Target attribute index crossing the boundary.
+ If there are no attributes to remove, the `-r` parameter can be omitted.

# 5. Deployment and operation

Operating environment : jre1.8.0_131

Java environment variables must be configured well in Windows environment.

There are two ways to use it. They are:

+ Simply click on the `SSMLSP.bat` file to run;
+ Add the unzipped folder to the path environment variable and run under CMD by typing the command `ssmlsp`.

# 6. Licensing and authorization

This application is licensed through GNU General Public License version 3 (GPLv3).

# 7. Seek help, question feedback?

You can submit Issues or contact me by e-mail(fei#feily.tech, Replace with @).
