# Android Cook Book

## OOP

#### 개념적
        함수의 재사용성이 높아진다.
* Model은 DAO가 가지고 있어야 한다. Presenter에서 직접 Model을 갖고 있는 것이 아니라. Dao에서 넘겨받는 것이다.
DAO는 Model을 네트워크, DB, 파일에서 가져 올 수 있는 것이다. Presenter에서는 Model을 직접 소유하고 있으면 안된다. ArrayList<> 형태로 소유 X
DAO에서 가져와야 한다. getList형태로.... DAO가 그런 역할을 하는 것....

* network로 local로  data를 생성하고 다루는 것은 모두 이것으로 관리하는 것이다. new Chat(); 을 바로 사용하는 것이 아니다. remoteChatDAO
localChatDAO, DAO를 가져오는게 네트워크에서 가져올수도 있고...

* 객체지향은 결국 클래스의 재사용성(인터페이스 등), 함수의 재사용성(함수의 가장 작은 단위로 쪼개는 것.)

* 클래스 안에서 다른 클래스를 생성(new)를 하는 것을 지양한다. 의존성이 생기기 때문이다.(해당 클래스가 없으면 동작하지 않음) 싱글턴이나 멀티턴으로 대체한다.

* 객체지향으로 작성된 코드는 TDD에 유리하다. 객체지향 = 클래스간 의존도가 낮다. = 서로 독립적으로 존재할 수 있다.
서로 독립적으로 존재하면 TDD를 사용할때 다른 클래스가 필요하지 않다. 또한 기능이 완전히 구현되어 있지 않더라도 같은 Interface를
상속받은 Dummy Class를 작성함으로써 쉽게(? 물론 테스트를 위한 코드를 작성해주어야한다.) 테스트를 할 수 있다.

* REST API도 객체지향과 마찬가지로 느슨한 결합을 지향한다. DB 의존성이 생기지 않고 정해진 규약에 의해서 호출하고 데이터를 받아온다.
Realm이나 Firebase는 코드가 프로젝트 안으로 들어와버리므로 의존성이 생겨버린다.

#### 방법론
* 구현체에서 상위 클래스나 인터페이스에 의존한다.

* 인터페이스나 클래스안에 인터페이스를 구현하여 Callback으로 사용한다.(Thread를 기다린다.) 

* 함수를 정리하는 방법.... 여러가지 일을 하는 함수들은 하나의 일만 처리하는 함수로 쪼개준다.
주석은 각각의 함수에다가 써주면 된다.  속성별, 기능별, 단위별로 분리하는 것. 함수 하나에
코드 한두줄 밖에 없다. 예를들어. 마커를 만드는 함수는 마커만 만든다. 이 함수가 마커를 만들고
사용하고 여러개로 동작하지 않는다. **함수가 실행 될 수 있는 최소 단위로 쪼개는 것**

* 함수를 가장 작은 단위로 쪼개서 클래스 내에서만 사용하면 private로 선언하게 되지만 다른 곳에서 접근하는 함수가 생기기 마련이고
    이러면 함수를 재사용하는 것이고 public으로 변환되고 이런것이 많아지면 비슷한 성질의 것끼리 묶어서 다른 클래스로 만드는 것이다.

* 제일 분리가 잘되어있는 언어 중 하나가 자바스크립트.. 자바스크립트 코드를 보면.. 알 수 있다.

    /* 함수형 언어의 핵심.(객체지향에서 이것을 요구한다.)
        속성별로 분리를 하는것. 기능별로 분리하는 것. 단위별로 분리하는 것.
        함수 하나에 코드 한두줄 밖에 없다. 마커를 만드는 것은 마커를
        함수가 실행될 수 있는 최소단위로 쪼개는 것. 원자 단위로 쪼개는 것.

## 생각해볼만한 주제

#### Singleton vs Static
What makes you say that either a singleton or a static method isn't thread-safe? Usually both should be implemented to be thread-safe.

The big difference between a singleton and a bunch of static methods is that singletons can implement interfaces (or derive from useful base classes, although that's less common, in my experience), so you can pass around the singleton as if it were "just another" implementation.

#### Singleton vs multiton

#### Singleton with Arguments in Java
[Link here](https://stackoverflow.com/questions/1050991/singleton-with-arguments-in-java)

I'll make my point very clear: a singleton with parameters is not a singleton.
A singleton, by definition, is an object you want to be instantiated no more than once. If you are trying to feed parameters to the constructor, what is the point of the singleton?
You have two options. If you want your singleton to be initialized with some data, you may load it with data after instantiation, like so:
```
SingletonObj singleton = SingletonObj.getInstance();
singleton.init(paramA, paramB); // init the object with data
```
If the operation your singleton is performing is recurring, and with different parameters every time, you might as well pass the parameters to the main method being executed:

but..

sorry, thats not true. there are situations where you have to pass in dynamicly created parameters which stay the same for the hole application runtime. so you cant use a constant within the singleton but have to pass that constant when its created. after passed once its the same constant for the hole time. a setter wont do the job if you need that specific constant within the constructor. – masi Nov 5 '11 at 17:57

#### newInstance

## Design Pattern

#### Factory
Need to study and examine below code.
```java 
public final class UsefulObjFactory {
    private static Map<Integer, UsefulObj> store =
        new HashMap<Integer, UsefulObj>();
    public static final class UsefulObj {
        private UsefulObj(int parameter) {
            // init
        }
        public void someUsefulMethod() {
            // some useful operation
        }
    }
    public static UsefulObj get(int parameter) {
        synchronized (store) {
            UsefulObj result = store.get(parameter);
            if (result == null) {
                result = new UsefulObj(parameter);
                store.put(parameter, result);
            }
            return result;
        }
    }
}

pulbic class CustomUsefulObj extends UsefulObjFactory.UsefulObj {
}
```


## MVP

### MVP(Activity and View)
This type uses activity as presenter, So you just extract **View** from **Acitivity**
This activity is conisist of three activity
**MainActivity, ListActivity, DetailActivit**

One of sampe Activity is here
+ `ComportMvpListActivity(presenter)`
    ```java
    public class ComportMvpListActivity extends AppCompatActivity {
        private ArrayList<Memo> memoList;
        private ComportMvpListView view;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    
            /*  Separate view from activity(presenter)
                Layout is also belong to view, so move it */
            this.view = new ComportMvpListView(this);
    
            /* get Data for Adapter */
            memoList = Loader.getMemoList();
    
            /* Create Adapter */
            RecyclerView.Adapter adapter = new ComportMvpListRvAdapter(memoList);
    
            /* Set Adapter */
            this.view.setAdapter(adapter);
        }
    }
    ```

+ `MvpListView`
    ```java
    public class ComportMvpListView {
    
        Activity activity;
        RecyclerView recyclerView;
    
        public ComportMvpListView(Activity activity) {
    
            this.activity = activity;
    
            /* Layout */
            activity.setContentView(R.layout.activity_comport_mvp_list);
    
            /* Dependecy Injection */
            this.recyclerView = (RecyclerView) activity.findViewById(R.id.comportMvpList_rv);
        }
    
        public void setAdapter(RecyclerView.Adapter adapter) {
            this.recyclerView.setAdapter(adapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.activity));
        }
    }
    ```

+ `ComportMvpListRvAdapter`
    
    You can also implement `RecyclerView` using MVP, and you can use **Adapter** as **Presenter** like above case,
    And **ViewHolder** as View, You can extract that. It's your choice. But in this case, View is implemented as inner Class.
    This case has advantage, View class can access easily to Presenter Class because of Closure(No need to parameter)
    Note : If you want to see separted view and presenter, refer to STRICT package.

    ```java
    public class ComportMvpListRvAdapter extends RecyclerView.Adapter<ComportMvpListRvAdapter.ViewHolder> {
    
    
        private  ArrayList<Memo> memoList;
    
        public ComportMvpListRvAdapter(ArrayList<Memo> memoList) {
            this.memoList = memoList;
        }
    
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comport_mvp_list_rv, null);
            return new ViewHolder(view);
        }
    
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Memo memo = memoList.get(position);
            holder.setId(memo.getId());
            holder.setName(memo.getId());
            holder.setTitle(memo.getTitle());
            holder.setDate(memo.getDate());
        }
    
        @Override
        public int getItemCount() {
            return this.memoList.size();
        }
    
        public class ViewHolder extends RecyclerView.ViewHolder {
    
            String id;
            TextView textViewTitle, textViewName, textViewDate;
    
    
            public ViewHolder(View itemView) {
                super(itemView);
    
                /* Dependency Injection */
                this.textViewTitle = (TextView) itemView.findViewById(R.id.comportMvpList_tv_title);
                this.textViewName = (TextView) itemView.findViewById(R.id.comportMvpList_tv_name);
                this.textViewDate = (TextView) itemView.findViewById(R.id.comportMvpList_tv_date);
    
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /* You can access to Presenter Class Method, because of closure */
                        goDetail(id, v.getContext());
                    }
                });
            }
    
            public void setId(String id) {
                this.id = id;
            }
    
            public void setTitle(String title) {
                this.textViewTitle.setText(title);
            }
    
            public void setName(String name) {
                this.textViewName.setText(name);
            }
    
            public void setDate(String date) {
                this.textViewDate.setText(date);
            }
        }
    
        private void goDetail(String id, Context context) {
            Intent intent = new Intent(context, ComportMvpActivityDetail.class);
            intent.putExtra("ID_KEY", id);
            context.startActivity(intent);
        }
    }
  ```
  
### MVP(Activity and Presenter, View)

## Util