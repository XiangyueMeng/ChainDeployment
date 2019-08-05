ip=(
    mengxiangyue@47.254.37.37
)
pwd
echo "Start ssh deployment"

 for i in ${ip[@]}; do
     ssh -p 22008 $i "rm -rf java-tron"
     ssh -p 22008 $i "mkdir java-tron"
     echo "made the directory"
     scp -P 22008 ./java-tron/build/libs/FullNode.jar $i:./java-tron/
     echo "uploaded the jar"
     scp -P 22008 ./java-tron/src/main/resources/temp.conf $i:./java-tron/
     echo "uploaded the config file"
     ssh -p 22008 $i "java -jar java-tron/FullNode.jar -c  java-tron/temp.conf &" &
     echo "Start Running the Node"
 done


echo  "Finish"

