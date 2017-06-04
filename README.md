# Android Cook Book

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

## Util