阻塞状态指的是堵塞在锁中,另外的线程占有锁,当其他的线程释放锁后,阻塞线程(在阻塞队列(有这个东西吗?假设有吧))中被分配到
锁后,阻塞线程会自动执行.
等待状态是指线程在等待(等待队列(这个是肯定有的))中在运行线程执行完后,执行notify/notifyAll 方法后,才会被唤醒,如果在等
待队列中被分配到锁后,才会执行.
两者的区别在于一个是前者是主动,后者是被动.

思考线程：
Q：什么是线程？

A: 操作系统的一个基本工作最小单元

Q: 基于java角度来说，为什么要用多线程

A：增加系统资源的利用率，加快代码的运行速度。

Q: 那么单个线程怎么工作？

A: 详情如下:
<div class="List-item"><div class="ContentItem AnswerItem" data-za-index="0" data-zop="{&quot;authorName&quot;:&quot;大闲人柴毛毛&quot;,&quot;itemId&quot;:252912242,&quot;title&quot;:&quot;Java线程中wait状态和block状态的区别?&quot;,&quot;type&quot;:&quot;answer&quot;}" name="252912242" itemprop="acceptedAnswer" itemtype="http://schema.org/Answer" itemscope="" data-za-detail-view-path-module="AnswerItem" data-za-extra-module="{&quot;card&quot;:{&quot;has_image&quot;:false,&quot;has_video&quot;:false,&quot;content&quot;:{&quot;type&quot;:&quot;Answer&quot;,&quot;token&quot;:&quot;252912242&quot;,&quot;upvote_num&quot;:32,&quot;comment_num&quot;:7,&quot;publish_timestamp&quot;:null,&quot;parent_token&quot;:&quot;27654579&quot;,&quot;author_member_hash_id&quot;:&quot;a2e67f2f8129c541265f5737236bf9f8&quot;}}}"><meta itemprop="image" content="https://pic1.zhimg.com/v2-6fdc939e35ec801c31ea515e80a88367_200x112.jpg"><meta itemprop="upvoteCount" content="32"><meta itemprop="url" content="https://www.zhihu.com/question/27654579/answer/252912242"><meta itemprop="dateCreated" content="2017-10-31T09:43:15.000Z"><meta itemprop="dateModified" content="2017-11-03T02:15:10.000Z"><meta itemprop="commentCount" content="7"><div class="RichContent RichContent--unescapable"><div class="RichContent-inner"><span class="RichText ztext CopyrightRichText-richText" itemprop="text"><p>先来一张线程状态转化图，然后我再慢慢解释：</p><figure><noscript><img src="https://pic4.zhimg.com/v2-6fdc939e35ec801c31ea515e80a88367_b.jpg" data-caption="" data-rawwidth="795" data-rawheight="541" class="origin_image zh-lightbox-thumb" width="795" data-original="https://pic4.zhimg.com/v2-6fdc939e35ec801c31ea515e80a88367_r.jpg"></noscript><img src="https://pic4.zhimg.com/80/v2-6fdc939e35ec801c31ea515e80a88367_hd.jpg" data-caption="" data-rawwidth="795" data-rawheight="541" class="origin_image zh-lightbox-thumb lazy" width="795" data-original="https://pic4.zhimg.com/v2-6fdc939e35ec801c31ea515e80a88367_r.jpg" data-actualsrc="https://pic4.zhimg.com/v2-6fdc939e35ec801c31ea515e80a88367_b.jpg"></figure><p>在Java中线程的状态一共被分成6种：</p><h2><b>初始态：NEW</b></h2><p>创建一个Thread对象，但还未调用start()启动线程时，线程处于初始态。</p><h2><b>运行态：RUNNABLE</b></h2><p>在Java中，运行态包括就绪态 和 运行态。</p><ul><li>就绪态  </li><ul><li>该状态下的线程已经获得执行所需的所有资源，只要CPU分配执行权就能运行。</li><li>所有就绪态的线程存放在就绪队列中。</li></ul><li>运行态  </li><ul><li>获得CPU执行权，正在执行的线程。</li><li>由于一个CPU同一时刻只能执行一条线程，因此每个CPU每个时刻只有一条运行态的线程。</li></ul></ul><h2><b>阻塞态</b></h2><ul><li>当一条正在执行的线程请求某一资源失败时，就会进入阻塞态。</li><li>而在Java中，阻塞态专指请求锁失败时进入的状态。</li><li>由一个阻塞队列存放所有阻塞态的线程。</li><li>处于阻塞态的线程会不断请求资源，一旦请求成功，就会进入就绪队列，等待执行。</li></ul><p>PS：锁、IO、Socket等都资源。</p><h2><b>等待态</b></h2><ul><li>当前线程中调用wait、join、park函数时，当前线程就会进入等待态。</li><li>也有一个等待队列存放所有等待态的线程。</li><li>线程处于等待态表示它需要等待其他线程的指示才能继续运行。</li><li>进入等待态的线程会释放CPU执行权，并释放资源（如：锁）</li></ul><h2><b>超时等待态</b></h2><ul><li>当运行中的线程调用sleep(time)、wait、join、parkNanos、parkUntil时，就会进入该状态；</li><li>它和等待态一样，并不是因为请求不到资源，而是主动进入，并且进入后需要其他线程唤醒；</li><li>进入该状态后释放CPU执行权 和 占有的资源。</li><li><b>与等待态的区别：</b>到了超时时间后自动进入阻塞队列，开始竞争锁。</li></ul><h2><b>终止态</b></h2><p>线程执行结束后的状态。</p><p><br></p><h2><b>注意：</b></h2><ul><li>wait()方法会释放CPU执行权 和 占有的锁。</li><li>sleep(long)方法仅释放CPU使用权，锁仍然占用；线程被放入超时等待队列，与yield相比，它会使线程较长时间得不到运行。</li><li>yield()方法仅释放CPU执行权，锁仍然占用，线程会被放入就绪队列，会在短时间内再次执行。</li><li>wait和notify必须配套使用，即必须使用同一把锁调用；</li><li>wait和notify必须放在一个同步块中</li><li>调用wait和notify的对象必须是他们所处同步块的锁对象。</li></ul></span></div><div><div class="ContentItem-time"><a target="_blank" href="/question/27654579/answer/252912242"><span data-tooltip="发布于 2017-10-31 17:43">编辑于 2017-11-03</span></a></div></div><div></div></div></div></div>

Q: 线程之间如何通信？

参考文件夹：communicate.
