# GsonRecyclerView

Practice android asyntask and recycler view

Requirement:
Use 2 methods of Thread lesson: Thread - runOnUI or ExecuteService , load json data from this url:

url = https://reqres.in/api/users?page=2

Parse json data (use Gson) and store data to a list of Person objects. Display on recyclerview.

Guide:
Import gson lib on gradle (module level) file:

		implementation 'com.google.code.gson:gson:2.8.6'


Code to get data from url:


                HttpsURLConnection connection = (url)
		 url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputstream = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputstream));
                String line;
                String text = "";
                while ((line = rd.readLine()) != null) {
                    text += line;
                }
                JSONObject obj = null;
                obj = new JSONObject(text);
                //String username = obj.getJSONObject("author").getString("username");
                JSONArray posts = obj.getJSONArray("data");
                Gson gson = new Gson(); // khởi tạo Gson
                Person[] list = gson.fromJson(posts.toString(), Person[].class);
                Log.i("datahhhhhhhh", "" + list.length);
                return list;


-	Load image from url to view: use library Glide (or Picasso, but Glide is recommended):
Guide use glide:



Add dependency to gradle (module level)
// Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
  implementation 'com.github.bumptech.glide:glide:4.11.0'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

Code to apply glide:

Glide.with(context).load(link).into(holder.imv_icon);


