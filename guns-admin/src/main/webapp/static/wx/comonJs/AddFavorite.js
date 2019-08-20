/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function AddFavorite(url, title)
{
try {
   if ((typeof window.sidebar == 'object')
     && (typeof window.sidebar.addPanel == 'function')) {
    window.sidebar.addPanel(title, url, '');
   } else { //IE
    window.external.AddFavorite(url, title);
   }
  } catch (e) {
   alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
  }
}



